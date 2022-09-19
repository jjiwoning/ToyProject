package jiwon.board.controller;

import jiwon.board.domain.Board;
import jiwon.board.domain.Member;
import jiwon.board.dto.BoardReadDto;
import jiwon.board.dto.BoardSearchCondition;
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
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //게시글 작성
    @GetMapping("/write")
    public String writeForm(@ModelAttribute BoardWriteDto boardWriteDto) {
        return "boards/writeBoardForm";
    }

    @PostMapping("/write")
    public String write(@Valid @ModelAttribute BoardWriteDto boardWriteDto, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "boards/writeBoardForm";
        }

        HttpSession session = request.getSession(false);
        if (session != null) {
            Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
            Board board = boardWriteDto.toEntity();
            boardService.write(board, member.getId());
        }
        return "redirect:/";
    }

    //게시글 1건 조회
    @GetMapping("/{id}")
    public String findOne(@PathVariable Long id, Model model) {
        BoardReadDto findDto = boardService.findOne(id);
        model.addAttribute("boardDto", findDto);
        return "boards/board";
    }

    //게시글 리스트 조회 + 검색 조건
    @GetMapping
    public String getList(Model model, @ModelAttribute BoardSearchCondition condition, @RequestParam(defaultValue = "1") long page) {
        List<Board> boards = boardService.findList(condition, page);
        model.addAttribute("boards", boards);
        return "boards/boardList";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model, HttpServletRequest request) {
        Board board = boardService.findById(id);

        String x = checkBoardMember(request, board);
        if (x != null) return x;

        BoardWriteDto boardWriteDto = new BoardWriteDto();
        boardWriteDto.toDto(board);
        model.addAttribute("boardWriteDto", boardWriteDto);
        return "boards/editBoardForm";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @Valid @ModelAttribute BoardWriteDto boardWriteDto, BindingResult bindingResult, HttpServletRequest request) {
        Board board = boardService.findById(id);

        String x = checkBoardMember(request, board);
        if (x != null) return x;

        if (bindingResult.hasErrors()) {
            return "boards/editBoardForm";
        }

        boardService.update(board.getId(), boardWriteDto.getTitle(), boardWriteDto.getContents());

        return "redirect:/boards/{id}";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, HttpServletRequest request) {

        Board board = boardService.findById(id);

        String x = checkBoardMember(request, board);
        if (x != null) return x;

        boardService.delete(id);
        return "redirect:/boards";
    }

    private String checkBoardMember(HttpServletRequest request, Board board) {
        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        if (!board.isWritten(member.getId())) {
            return "redirect:/boards";
        }
        return null;
    }
}
