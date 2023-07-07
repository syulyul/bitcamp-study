package bitcamp.report.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import bitcamp.report.vo.Board;

public class BoardNetworkDao implements BoardDao {

  String dataName;
  DataInputStream in;
  DataOutputStream out;

  public BoardNetworkDao(String dataName, DataInputStream in, DataOutputStream out) {
    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void insert(Board board) {
    try {
      Gson gson = new Gson();
      // 서버에서 보낼 명령과 데이터를 Map 객체에 담는다.
      HashMap<String, Object> request = new HashMap<>();

      request.put("command", dataName + "/insert");
      request.put("data", gson.toJson(board));

      // Map 객체에 담은 정보를 JSON 문자열로 변환하여 서버에 보낸다.
      out.writeUTF(gson.toJson(request));

      // 명령을 실행할 때 사용할 데이터를 보낸다.
      String jsonStr = gson.toJson(board);
      out.writeUTF(jsonStr);

      // 서버에서 보낸 응답을 받는다.
      Map<String, Object> response = gson.fromJson(in.readUTF(), Map.class);
      if (!response.get("status").equals("success")) {
        throw new RuntimeException((String) response.get("message"));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Board> list() {
    try {
      out.writeUTF(dataName + "/list");

      Gson gson = new Gson();
      Map<String, Object> response = gson.fromJson(in.readUTF(), Map.class);
      if (!response.get("status").equals("success")) {
        throw new RuntimeException((String) response.get("message"));
      }

      return gson.fromJson((String) response.get("data"),
          TypeToken.getParameterized(List.class, Board.class).getType());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Board findBy(int no) {
    return null;
  }

  @Override
  public int update(Board board) {
    return 0;
  }

  @Override
  public int delete(int no) {
    return 0;
  }

}
