package jiwon.board.controller;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import jiwon.board.dto.BoardWriteDto;
import jiwon.board.service.BoardService;
import jiwon.board.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

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

}
