package bitcamp.util;

public interface List {
  boolean add(Object value); // public abstract 는 컴파일러가 자동으로 붙여줄 것 (Object value : 시그너처)

  Object get(int index);

  Object[] toArray();

  Object remove(int index);

  boolean remove(Object value);

  int size();
}
