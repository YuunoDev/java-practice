package games.chess;

//posición       ("e",1)
record Position(String letra, int numero) {
}

enum Color {
    B,
    W
}

public class piece {
    private String name;
    private Position position;
    private Color color;
    private Boolean first;
    private Boolean isActive;

    public piece(String name, Position position, Color color)
    {
        this.name = name;
        this.position = position;
        this.color = color;
        this.first = false;
        this.isActive = true;
    }

    public piece(String name, Position position, Color color, Boolean first) {
        this.name = name;
        this.position = position;
        this.color = color;
        this.first = first;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    

}
