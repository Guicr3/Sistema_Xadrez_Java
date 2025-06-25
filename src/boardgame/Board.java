package boardgame;

public class Board {
    private Integer rows;
    private Integer columns;
    
    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        if(rows < 1 || columns < 1){
            throw new BoardException("O tabuleiro deve ter, pelo menos, 1 linha e 1 coluna");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public Integer getColumns() {
        return columns;
    }

    public Integer getRows() {
        return rows;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public void setPieces(Piece[][] pieces) {
        this.pieces = pieces;
    }
    
    public Piece piece(int row, int column){
        if(!positionExists(row, column)){
            throw new BoardException("Posicao inexistente");
        }
        return pieces[row][column];
    }
    
    public Piece piece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Posicao inexistente");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position){
        if(thereIsAPiece(position)){
            throw new BoardException("Ja tem uma peça na posicao " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }
    
    public Piece removePiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Posicao inexistente");
        }
        if(piece(position) == null){
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }
    
    private boolean positionExists(int row, int column){
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }
    
    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }
    
    public boolean thereIsAPiece(Position position){
        if(!positionExists(position)){
            throw new BoardException("Posicao inexistente");
        }
        return piece(position) != null;
    }
}
