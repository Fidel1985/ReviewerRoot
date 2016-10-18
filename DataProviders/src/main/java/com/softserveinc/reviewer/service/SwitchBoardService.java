package com.softserveinc.reviewer.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.softserveinc.reviewer.api.FriendRelation;

public class SwitchBoardService {

    private static final List<FriendRelation> FRIEND_RELATIONS = Arrays.asList(new FriendRelation("1", "1"), new FriendRelation("1", "2"), new FriendRelation("1", "3"),
            new FriendRelation("2", "1"), new FriendRelation("2", "3"), new FriendRelation("3", "2"));

    public List<FriendRelation> getSwitchBoardByDestinationClientId(String destinationClientId) {
        return FRIEND_RELATIONS.stream().filter(x -> x.getDestinationClientId().equals(destinationClientId)).collect(Collectors.toList());
    }
}
