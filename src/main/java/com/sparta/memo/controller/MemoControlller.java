package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoControlller {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping("memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // RequestDto => Entity
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = !memoList.isEmpty() ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }


    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        // Map To List
        List<MemoResponseDto> responseList = memoList.values().stream()
                .map(MemoResponseDto::new).toList();
        return responseList;
    }
}
