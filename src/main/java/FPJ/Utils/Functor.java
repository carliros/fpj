package FPJ.Utils;

public interface Functor<T, U> {
    U fmap(T t);
}
