package chess;

import boardgame.Position;
import exceptions.ChessException;

public class ChessPosition {

    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if ((column < 'A' || column > 'H') || (row < 1 || row > 8)){
            throw new ChessException("Error instantiating ChessPosition. Valid" +
                    "values are from A1 to H8");
        }
        this.column = column;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public char getColumn() {
        return column;
    }

    protected Position toPosition(){
        return new Position(8 - row, column - 'A');
    }

    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char)('A' - position.getColumn()), 8 - position.getRow());
    }

    @Override
    public String toString(){
        return " " + column + row;
    }
}
