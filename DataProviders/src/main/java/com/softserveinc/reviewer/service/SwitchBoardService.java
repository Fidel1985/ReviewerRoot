package com.softserveinc.reviewer.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.softserveinc.reviewer.api.SwitchBoard;

public class SwitchBoardService {

    private static final List<SwitchBoard> switchBoard = Arrays.asList(new SwitchBoard(1L, 1L), new SwitchBoard(1L, 2L), new SwitchBoard(1L, 3L),
            new SwitchBoard(2L, 1L), new SwitchBoard(2L, 3L), new SwitchBoard(3L, 2L));

    public List<SwitchBoard> getSwitchBoardById(long id) {
        return switchBoard.stream().filter(x -> x.getDestinationClientId() == id).collect(Collectors.toList());
    }
}
