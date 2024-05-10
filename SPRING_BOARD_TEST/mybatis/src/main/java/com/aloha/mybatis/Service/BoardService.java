package com.aloha.mybatis.Service;

import java.util.List;

import com.aloha.mybatis.dto.Board;

public interface BoardService {
    
    public List<Board> list();

    public Board select(int no);

    public int insert(Board board);

    public int update(Board board);

    public int delete(int no);
}
