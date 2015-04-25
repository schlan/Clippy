package at.droelf.clippy.model.raw;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Branching {

    private final List<Branch> branches;

    @JsonCreator
    public Branching(@JsonProperty("branches")List<Branch> branches) {
        this.branches = branches;
    }

    public List<Branch> getBranches() {
        return branches;
    }
}
