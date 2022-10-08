package com.ruse.world.content.instanceMananger;

import com.ruse.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GridSpawns {
	ONE_3(1,3, new Position(2783,4765,0),new Position(2785,4762,0), 2),
	ONE_4(1,4, new Position(2782,4765,0),new Position(2785,4762,0), 2),

	TWO_3(2,3, new Position(2781,4764,1),new Position(2785,4762,1), 3),
	TWO_4(2,4, new Position(2781,4764,1),new Position(2785,4762,1), 2),

	THREE_3(3,3, new Position(2782,4764,2),new Position(2785,4761,2), 3),
	THREE_4(3,4, new Position(2781,4763,3),new Position(2785,4760,3), 3),

	FOUR_3(4,3, new Position(2781,4763,3),new Position(2785,4762,3), 3),
	FOUR_4(4,4, new Position(2782,4763,3),new Position(2785,4762,3), 3),

	FIVE_3(5,3, new Position(2781,4763,3),new Position(2785,4762,3), 3),
	FIVE_4(5,4, new Position(2782,4763,3),new Position(2785,4762,3), 3),

	;
	private int npcSize;
	private int gridSize;
	private Position start;
	private Position playerPosition;
	private int offset;


}
