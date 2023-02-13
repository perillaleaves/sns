package project.reComment.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.reComment.response.ReCommentListResponse;
import project.reComment.response.ReCommentResponse;
import project.reComment.service.ReCommentQueryService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ReCommentQueryController {

    private final ReCommentQueryService reCommentQueryService;

    public ReCommentQueryController(ReCommentQueryService reCommentQueryService) {
        this.reCommentQueryService = reCommentQueryService;
    }

    @GetMapping("/comment/{commentId}/recomments")
    public Response<ReCommentResponse> getReComments(@PathVariable("commentId") Long commentId,
                                                     @PageableDefault Pageable pageable,
                                                     HttpServletRequest httpServletRequest) {
        Long userId = (Long) httpServletRequest.getAttribute("userId");
        ReCommentListResponse reCommentList = reCommentQueryService.findReCommentList(commentId, userId, pageable);
        return new Response<>(new ReCommentResponse(reCommentList));
    }

}