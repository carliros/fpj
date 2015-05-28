package FPJ.Utils;

import java.util.ArrayList;
import java.util.List;

public final class ListUtils {
    private static ListUtils instance = null;
    
    private ListUtils(){}
    
    public static ListUtils getInstance(){
        if (instance == null) {
            instance = new ListUtils();
        }
        
        return instance;
    }

    public <T, U> List<U> map(List<T> list, Map<T, U> function) {
        List<U> mapList = new ArrayList<U>();
        
        for (T t : list){
            U u = function.action(t);
            
            mapList.add(u);
        }

        return mapList;
    }

    public <T> void mapM_(List<T> list, MapAction<T> function) {
        for (T t : list){
            function.action(t);
        }
    }

    public <T> List<T> filter(List<T> list, Filter<T> function){
        List<T> filterList = new ArrayList<T>();

        for (T t : list){
            if (function.action(t))
                filterList.add(t);
        }

        return filterList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
