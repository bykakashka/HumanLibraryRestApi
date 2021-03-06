package com.byka.humanlibrary.controller;

import com.byka.humanlibrary.data.*;
import com.byka.humanlibrary.service.BoardService;
import com.byka.humanlibrary.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/events")
public class EventController {
    private static final Integer EVENTS_COUNT = 5;

    @Autowired
    private EventService eventService;

    @Autowired
    private BoardService boardService;

    @GetMapping(value = "/nearest")
    @ResponseBody
    public ListWrapper<EventData> getNearest() {
        return new ListWrapper<>(eventService.getLatest(EVENTS_COUNT));
    }

    @GetMapping(value = "/catalog/{eventId}")
    @ResponseBody
    public ListWrapper<BookData> getCatalog(@PathVariable Long eventId) {
        return new ListWrapper<>(eventService.getCatalog(eventId));
    }

    @GetMapping(value = "/sessions/{eventId}")
    @ResponseBody
    public ListWrapper<SessionData> getSessions(@PathVariable Long eventId) {
        return new ListWrapper<>( eventService.getSessions(eventId));
    }

    @GetMapping(value = "/boards/{sessionId}")
    @ResponseBody
    public ListWrapper<BoardData> getBoards(@PathVariable Long sessionId) {
        return new ListWrapper<>( boardService.getBySessionId(sessionId));
    }
}
