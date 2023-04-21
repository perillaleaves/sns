package project.search.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.common.response.Response;
import project.search.request.SearchRequest;
import project.search.response.SearchListResponse;
import project.search.response.SearchResponse;
import project.search.service.SearchQueryService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SearchQueryController {

    private final SearchQueryService searchQueryService;

    public SearchQueryController(SearchQueryService searchQueryService) {
        this.searchQueryService = searchQueryService;
    }

    @GetMapping("/nickname/search")
    public Response<SearchResponse> searchUserListByName(
            @RequestParam(value = "userId", required = false) Long lastUserId,
            SearchRequest request,
            @PageableDefault Pageable pageable,
            HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        SearchListResponse searchList = searchQueryService.findUserListByName(lastUserId, loginUserId, request, pageable);
        return new Response<>(new SearchResponse(searchList));
    }

    @GetMapping("/search")
    public Response<SearchResponse> searchUserList(
            @RequestParam(value = "userId", required = false) Long lastUserId,
            @PageableDefault Pageable pageable,
            HttpServletRequest httpServletRequest) {
        Long loginUserId = (Long) httpServletRequest.getAttribute("userId");
        SearchListResponse searchList = searchQueryService.findUserList(lastUserId, loginUserId, pageable);
        return new Response<>(new SearchResponse(searchList));
    }

}