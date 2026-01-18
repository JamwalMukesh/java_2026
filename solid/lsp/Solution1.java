// Liskov Substitution Priniciple
abstract class Shape{
	abstract int getArea();
}
class Rectangle extends Shape{
	private final int width, height;

	public Rectangle(int width, int height){
		this.width = width;
		this.height = height;
	}

	public int getWidth(){
		return this.width;
	}

	public int getHeight(){
		return this.height;
	}

	@Override
	public int getArea(){
		return width * height;
	}

	@Override
	public String toString(){
		return "Rectangle{" + 
				" width = " + width + 
				" height = " + height +
				"}";
	}
}

class Square extends Shape{

	private final int size;

    public Square(int size) {
        this.size = size;
    }

    @Override
    public int getArea() {
        return size * size;
    }

	@Override
	public void setHeight(int height){
		super.setWidth(height);
		super.setHeight(height);
	}
}

class Solution1{

	public static void main(String[] args){
		
	}
}