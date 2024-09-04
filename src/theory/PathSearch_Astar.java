package theory;

import java.util.ArrayList;
import java.util.List;

//A스타 알고리즘 : 최단거리경로 탐색
public class PathSearch_Astar {
    public static void main(String[] args) {
        //0(갈수있는 길), 벽(1~8)
        Integer[][] map = {
                {0, 0, 1, 0, 0, 0, 3, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 3, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 3, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 3, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 3, 0, 2, 0},
                {0, 0, 0, 0, 0, 0, 3, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        Astar astar = new Astar();
        astar.map = map;

        astar.findPath();
    }
}

class Astar {

    Integer[][] map;

    Integer rowSize; // 맵의 크기 저장 변수
    Integer colSize;

    AstarNode start; //출발지
    AstarNode goal;  //도착지

    List<AstarNode> openList;
    List<AstarNode> closeList;

    public void findPath() {
        //탐색을 위한 position
        //int[][] position = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}}; //직선으로만 진행
        int[][] position = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}}; //대각선으로 진행

        rowSize = map.length;
        colSize = map[0].length;

        start = new AstarNode(1,0);
        goal = new AstarNode(8,3);

        openList = new ArrayList<AstarNode>();
        closeList = new ArrayList<AstarNode>();

        openList.add(start);

        while (!openList.isEmpty())
        {
            AstarNode currentNode = openList.get(0);
            int currentIdx = 0;

            for (int i = 0; i < openList.size(); i++) //openList에서 f(총비용)이 가장 작은 노드 찾기
            {
                if(openList.get(i).f < currentNode.f){
                    currentNode = openList.get(i);
                    currentIdx = i;
                }
            }

            openList.remove(currentIdx); //f가장 작은 노드를 openList에서 제거
            closeList.add(currentNode);  //openList에서 꺼낸 node를 closeList에 추가

            //목표점 도착하였을 경우
            if(currentNode.x == goal.x && currentNode.y == goal.y){
                while(currentNode != null){
                    map[currentNode.y][currentNode.x] = 9; //도착 map 숫자를 9로 변경
                    currentNode = currentNode.parents;     //부모 node를 추적
                }

                //결과출력
                for(int i = 0; i < map.length; i++){
                    for(int j = 0; j < map[0].length; j++){
                        if(map[i][j] == 0){
                            System.out.print("⬜\uFE0F");     //흰색
                        }else if(map[i][j] == 9){
                            System.out.print("\uD83D\uDFE6"); //파란색
                        }else{
                            System.out.print("\uD83D\uDFE7"); //주황색
                        }
                    }
                    System.out.println();
                }
                break;
            }

            //현재 노드를 기준으로 탐색할 노드 list
            List<AstarNode> childNodes = new ArrayList<AstarNode>();

            for(int[] newPosition : position){ //현재 지점을 기준으로 8곳 탐색
                int row = currentNode.x + newPosition[0];
                int col = currentNode.y + newPosition[1];

                //map범위 밖이면 continue;
                if(map.length <= col || map[0].length <= row || col <0 || row < 0 )
                    continue;

                //장애물이면 continue;
                if(map[col][row] != 0 )
                    continue;

                //탐색할 노드 제작
                AstarNode childNode = new AstarNode(row, col);

                //이미 탐색한 노드이면 continue;
                boolean pass = false;
                for(AstarNode node : closeList){
                    if(node.x == childNode.x && node.y == childNode.y){
                        pass = true;
                        break;
                    }
                }
                for(AstarNode node : openList){
                    if(node.x == childNode.x && node.y == childNode.y){
                        pass = true;
                        break;
                    }
                }
                if(pass) continue;

                //g,h,f 계산 후 node 세팅
                int g = currentNode.g + 1;  //시작점 부터 숫자
                int h = (Math.abs(childNode.x - goal.x) + Math.abs(childNode.y - goal.y)) * 10; //목적지 까지 숫자
                int f = childNode.g + childNode.h; //총비용
                childNode.setAstarNode(f, g, h,currentNode);

                childNodes.add(childNode);  //현재 기준 탐색할 nodeList

                openList.add(childNode);    //openList에 childNodes 추가
            }

        }
    }
}


class AstarNode {

    Integer x;
    Integer y;
    Integer f; //총비용
    Integer g; //시작점 부터 숫자
    Integer h; //목적지 까지 숫자

    AstarNode parents;

    public AstarNode(Integer x, Integer y) {
        this.x = x;
        this.y = y;
        this.f=0;
        this.g=0;
        this.h=0;
    }

    public void setAstarNode(Integer f, Integer g, Integer h, AstarNode parents) {
        this.f = f;
        this.g = g;
        this.h = h;
        this.parents = parents;
    }
}
