package com.mygdx.game.model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import com.mygdx.game.model.Node;


public class Asearch {
	
    private List<Node> openList;
    private List<Node> closeList;
    public List<Node> resultList;
    private final int COST = 1;
	private int[][] map;
	private int playerX;
	private int playerY;
	private int aimX;
	private int aimY;
	public Node start;
	public Node end;
	
	

	public Asearch(int[][]map, int playerX, int playerY, int aimX, int aimY){
	        this.map = map;
	        this.playerX = playerX;
	        this.playerY = playerY;
	        this.aimX = aimX;
	        this.aimY = aimY;
            openList = new ArrayList<Node>();
	        closeList = new ArrayList<Node>();
	    	start.setX(playerX);
	    	start.setY(playerY);
	    	end.setX(aimX);
	    	end.setY(aimY);
	    	resultList = search(start, end);
	    }

    private List<Node> search(Node start,Node end){
    	this.start = start;
    	this.end = end;
	
    	openList.add(start);
        List<Node> resultList=new ArrayList<Node>();
        boolean isFind=false;
        
        Node node=null;
        while(openList.size()>0){
            node=openList.get(0);
            if(node.getX()==end.getX()&&node.getY()==end.getY()){
                isFind=true;
                break;
            }
            
            if((node.getY()-1)>=0){
                checkPath(node.getX(),node.getY()-1,node, end, COST);
            }
            //下
            if((node.getY()+1)<map.length){
                checkPath(node.getX(),node.getY()+1,node, end, COST);
            }
            //左
            if((node.getX()-1)>=0){
                checkPath(node.getX()-1,node.getY(),node, end, COST);
            }
            //右
            if((node.getX()+1)<map[0].length){
                checkPath(node.getX()+1,node.getY(),node, end, COST);
            }
                
            closeList.add(openList.remove(0));
            //开启列表中排序，把F值最低的放到最底端
            Collections.sort(openList, new NodeFComparator());
        }
        if(isFind){
            getPath(resultList, node); 
        }
        return resultList;
    }
    
    private boolean checkPath(int x,int y,Node parentNode,Node end,int cost){
        Node node=new Node(x, y, parentNode);
        //查找地图中是否能通过
        if(map[x][y]==0){
            closeList.add(node);
            return false;
        }
        //查找关闭列表中是否存在
        if(isListContains(closeList, x, y)!=-1){
            return false;
        }
        //查找开启列表中是否存在
        int index=-1;
        if((index=isListContains(openList, x, y))!=-1){
            //G值是否更小，即是否更新G，F值
            if((parentNode.getG()+cost)<openList.get(index).getG()){
                node.setParentNode(parentNode);
                countG(node, cost);
                countF(node);
                openList.set(index, node);
            }
        }else{
            //添加到开启列表中
            node.setParentNode(parentNode);
            count(node, end, cost);
            openList.add(node);
        }
        return true;
    }
    
    //集合中是否包含某个元素(-1：没有找到，否则返回所在的索引)
    private int isListContains(List<Node> list,int x,int y){
        for(int i=0;i<list.size();i++){
            Node node=list.get(i);
            if(node.getX()==x&&node.getY()==y){
                return i;
            }
        }
        return -1;
    }
    
    //从终点往返回到起点
    private void getPath(List<Node> resultList,Node node){
    	this.resultList = resultList;
        if(node.getParentNode()!=null){
            getPath(resultList, node.getParentNode());
        }
        resultList.add(node);
    }
    
    //计算G,H,F值
    private void count(Node node,Node end, int cost){
        countG(node, cost);
        countH(node, end);
        countF(end);
    }
    //计算G值
    private void countG(Node node,int cost){
        if(node.getParentNode()==null){
            node.setG(cost);
        }else{
            node.setG(node.getParentNode().getG()+cost);
        }
    }
    //计算H值
    private void countH(Node node,Node end){
        node.setF(Math.abs(node.getX()-end.getX())+Math.abs(node.getY()-end.getY()));
    }
    //计算F值
    private void countF(Node node){
        node.setF(node.getG()+node.getF());
    }
    
}
//节点类
class NodeFComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
        return o1.getF()-o2.getF();
    }
}
    
