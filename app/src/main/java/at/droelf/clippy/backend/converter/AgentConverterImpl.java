package at.droelf.clippy.backend.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.droelf.clippy.backend.converter.mapping.AgentMapping;
import at.droelf.clippy.backend.converter.mapping.AgentMappingFactory;
import at.droelf.clippy.model.AgentType;
import at.droelf.clippy.model.gui.UiAgent;
import at.droelf.clippy.model.gui.UiAnimation;
import at.droelf.clippy.model.gui.UiBranch;
import at.droelf.clippy.model.gui.UiFrame;
import at.droelf.clippy.model.raw.Agent;
import at.droelf.clippy.model.raw.Animation;
import at.droelf.clippy.model.raw.Branch;
import at.droelf.clippy.model.raw.Branching;
import at.droelf.clippy.model.raw.Frame;

public class AgentConverterImpl implements AgentConverter {

    private final AgentMapping agentMapping;

    public AgentConverterImpl(AgentType agentType) {
        this.agentMapping = AgentMappingFactory.getMapping(agentType);
    }

    @Override
    public UiAgent agentToUiAgent(Agent agent) {
        return new UiAgent(
            agent.getOverlayCount(),
            agent.getFrameSize().get(0),
            agent.getFrameSize().get(1),
            convertAnimationMap(agent.getAnimations(), agent),
            agentMapping.getFirstFrameId()
        );
    }


    private Map<String, UiAnimation> convertAnimationMap(Map<String, Animation> animationMap, Agent agent){
        final Map<String, UiAnimation> uiAnimationMap = new HashMap<>();

        for(String key : animationMap.keySet()){
            final Animation animation = animationMap.get(key);
            uiAnimationMap.put(key, convertAnimation(animation, agent));
        }

        return uiAnimationMap;
    }

    private UiAnimation convertAnimation(Animation animation, Agent agent){
        final List<UiFrame> uiAnimationList = new ArrayList<>();

        final List<Frame> frames = animation.getFrames();
        for(Frame frame : frames){
            final UiFrame uiFrame = new UiFrame(
                    frame.getDuration(),
                    convertImageListToId(frame.getImages(), agent),
                    frame.getExitBranch(),
                    convetBranchingToUiBranches(frame.getBranching()),
                    (frame.getSound() != null ) ? agentMapping.getSoundMapping()[frame.getSound() - 1] : null
            );
            uiAnimationList.add(uiFrame);
        }

        return new UiAnimation(uiAnimationList);
    }


    private List<UiBranch> convetBranchingToUiBranches(Branching branching){
        if(branching == null){
            return null;
        }

        final List<UiBranch> uiBranches = new ArrayList<>();
        final List<Branch> branches = branching.getBranches();
        for(Branch branch : branches){
           uiBranches.add(new UiBranch(branch.getFrameIndex(), branch.getWeight()));
        }

        return uiBranches;
    }

    private List<Integer> convertImageListToId(List<List<Integer>> lists, Agent agent) {
        final List<Integer> result = new ArrayList<>();

        if(lists == null){
            return new ArrayList<Integer>(){{add(agentMapping.getEmptyFrameId());}};
        }

        for(List<Integer> imagePos : lists){
            result.add(imagePositionToId(
                getFrameWidth(agent),
                getFrameHeight(agent),
                agentMapping.getNumberColumns(),
                agentMapping.getNumberRows(),
                imagePos.get(0),
                imagePos.get(1)
            ));
        }

        return result;
    }

    private Integer imagePositionToId(int frameWidth, int frameHeight, int numberColumns, int numberRows, int posX, int posY){
        final int logicColumn = posX / frameWidth;
        final int logicRow = posY / frameHeight;
        final int posInMapping = numberColumns * logicRow+ logicColumn;

        return agentMapping.getMapping()[posInMapping];
    }


    private int getFrameWidth(Agent agent){
        return agent.getFrameSize().get(0);
    }

    private int getFrameHeight(Agent agent){
        return agent.getFrameSize().get(1);
    }
}
