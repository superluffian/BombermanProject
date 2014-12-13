package search;

import java.util.Comparator;

public class NodeFComparator implements Comparator<Node>{

  @Override
  public int compare(Node o1, Node o2) {
      return o1.getF()-o2.getF();
  }
}