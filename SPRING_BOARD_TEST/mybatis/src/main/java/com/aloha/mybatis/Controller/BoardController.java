package com.aloha.mybatis.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.mybatis.Service.BoardService;
import com.aloha.mybatis.dto.Board;

import lombok.extern.slf4j.Slf4j;




@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boardList = boardService.list();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/read")
    public String read(int no, Model model) {
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "board/read";
    }

    @GetMapping("/insert")
    public String insert() {
        return "board/insert";
    }


    @PostMapping("/insert")
    public String insertPro(Board board) {
        int result = boardService.insert(board);

        if(result>0){
            return "redirect:/board/list";
        }
        
        return "redirect:/board/insert?error";
    }
    

    @GetMapping("/update")
    public String update(Model model, int no) {
        Board board = boardService.select(no);
        model.addAttribute("board", board);
        return "board/update";
    }

    @PostMapping("/update")
    public String updatePro(Board board) {

        log.info(board.toString());

        int result = boardService.update(board);

        if(result>0){
            return "redirect:/board/read?no="+board.getNo();
        }
        
        return "redirect:/board/update?error&no="+board.getNo();
    }

    @PostMapping("/delete")
    public String deletePro(int no) {
        int result = boardService.delete(no);

        if(result>0){
            return "redirect:/board/list";
        }
        
        return "redirect:/board/read?error&no="+no;
    }
    
    


    


}
