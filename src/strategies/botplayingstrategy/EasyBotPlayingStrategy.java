package strategies.botplayingstrategy;

import models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move decideMove(Player player, Board board) {

        for(int i=0; i<board.getBoard().size(); i++){
            for(int j=0; j<board.getBoard().size(); j++){

                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    Cell cell = board.getBoard().get(i).get(j);

                    return new Move(cell,player);
                }
            }
        }

        return null;



    }
}
