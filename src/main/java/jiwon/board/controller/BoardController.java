package jiwon.board.controller;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import jiwon.board.dto.BoardPostDto;
import jiwon.board.dto.BoardWriteDto;
import jiwon.board.service.BoardService;
import jiwon.board.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //게시글 작성
    @GetMapping("/write")
    public String writeForm(@ModelAttribute BoardWriteDto boardWriteDto){
        return "boards/writeBoardForm";
    }

    @PostMapping("/write")
    public String write(@Valid @ModelAttribute BoardWriteDto boardWriteDto, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors()){
            return "boards/writeBoardForm";
        }

        HttpSession session = request.getSession(false);
        if(session != null){
            Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            Board board = boardWriteDto.toEntity();
            boardService.write(board, member.getId());
        }
        return "home";
    }

    //게시글 1건 조회
    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id, Model model) {
        BoardPostDto findDto = boardService.findOne(id);
        model.addAttribute("boardDto", findDto);
        return "boards/board";
    }

}
