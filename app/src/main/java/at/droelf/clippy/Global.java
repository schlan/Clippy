package at.droelf.clippy;


import at.droelf.clippy.backend.AgentService;
import at.droelf.clippy.backend.AgentServiceImpl;
import at.droelf.clippy.backend.source.AgentSource;
import at.droelf.clippy.backend.source.AgentSourceImpl;
import timber.log.Timber;


public enum Global {
    INSTANCE;

    private final AgentSource agentSource;
    private final AgentService agentService;
    private final Timber.Tree logTree;

    private Global(){
        this.agentSource = new AgentSourceImpl();
        this.agentService = new AgentServiceImpl(agentSource);
        this.logTree = new Timber.DebugTree();
    }

    public AgentService getAgentService() {
        return agentService;
    }

    public Timber.Tree getLogTree() {
        return logTree;
    }

    //    private class DebugTree extends Timber.Tree{
//
//        @Override
//        protected void log(int priority, String tag, String message, Throwable t) {
//            //Log.println(priority, tag, message);
//        }
//
//    }o

}
