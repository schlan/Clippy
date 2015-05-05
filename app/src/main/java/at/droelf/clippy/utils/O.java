package at.droelf.clippy.utils;

public class O<E>{

    private E data;
    private final boolean success;
    private String error;

    public O(E data){
        this.data = data;
        this.success = true;
    }

    public O(String error){
        this.success = false;
        this.error = error;
    }

    public String getError(){
        if(success){
            throw new RuntimeException("Success: " + success);
        }
        return error;
    }

    public E getData(){
        if(!success){
            throw new RuntimeException("Success: " + success);
        }
        return data;
    }

    public boolean isSuccess(){
        return success;
    }


}
