package boardgame;

public class Board {

    private int rows;
    private int colums;
    private Piece[][] pieces;

    public Board(int rows, int colums) {
        if (rows < 1 || colums < 1) {
            throw new BoardException("Erro ao criar o tabuleiro: deve haver pelo menos 1 linha e 1 coluna");
        }
        this.rows = rows;
        this.colums = colums;
        pieces = new Piece[rows][colums];
    }

    public int getRows() {
        return rows;
    }

    public int getColums() {
        return colums;
    }

    public Piece piece(int row, int column) {
        if (!positionExists(row, column)) {
            throw new BoardException("Nao tem essa posicao no tabuleiro");
        }
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Nao tem essa posicao no tabuleiro");
        }
        return  pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("Ja existe uma peca nessa posicao " + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Nao tem essa posicao no tabuleiro");
        }
        if (piece(position) == null) {
            return null;
        }

        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;

    }

    public boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < colums;
    }
    public boolean positionExists(Position position) {
        return  positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Nao tem essa posicao no tabuleiro");
        }
        return piece(position) != null;
    }

}
