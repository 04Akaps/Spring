public class DollarCalculator implements ICalculator{

    private int price = 1;
    private MarKetApi marKetApi;

    public DollarCalculator(MarKetApi marKetApi){ // 시세를 들고 오는코드(외부에서)
        this.marKetApi = marKetApi;
    }

    public void init(){
        this.price = marKetApi.connect();
    }

    @Override
    public int sum(int x, int y) {
        x *= price;
        y *= price;
        return x+y;
    }

    @Override
    public int minus(int x, int y) {
        x *= price;
        y *= price;
        return x-y;
    }
}
