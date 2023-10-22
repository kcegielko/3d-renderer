public class ImageData {

    protected int[][] data;

    public ImageData(int xSize, int ySize) {
        data = new int[xSize][ySize];
    }

    public void setData(int x, int y, int color) {
        data[x][y] = color;
    }

    public int getData(int x, int y) {
        return data[x][y];
    }

    public int getXSize() {
        return data.length;   
    }

    public int getYSize() {
        return data[0].length;   
    }
}
