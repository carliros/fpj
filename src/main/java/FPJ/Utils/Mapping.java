package FPJ.Utils;

import java.util.ArrayList;
import java.util.List;

public final class Mapping {
    private static Mapping instance = null;
    
    private Mapping (){}
    
    public static Mapping getInstance(){
        if (instance == null) {
            instance = new Mapping();
        }
        
        return instance;
    }

    public <T, U> List<U> map(List<T> list, Functor<T, U> function) {
        List<U> mapList = new ArrayList<U>();
        
        for (T t : list){
            U u = function.fmap(t);
            
            mapList.add(u);
        }

        return mapList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
