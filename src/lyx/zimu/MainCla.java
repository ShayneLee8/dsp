package lyx.zimu;

public class MainCla {
    private final static Double MU = 0.000005;
    private Double[] x1 = new Double[101];
    private Double[] x2 = new Double[10];
    private Double[][] wh = new Double[10][101];
    private Double[] wo = new Double[10];
    private double x3 = 0;
    private double elet = 0;
    /**
     * 设置随机权重
     */
    public void randomWeights(){
        for(int i=0;i<10;i++){
            wo[i] = Math.random()-0.5;
            for(int j=0;j<101;j++){
                wh[i][j] = (Math.random()-0.5)/1000;
            }
        }
    }
    public void meth2(){
        for(int i=0;i<800;i++){
            long esum = 0;
            for(int j=0;j<260;j++){
                subMeth1();
                subMeth2();
                esum += Math.pow(esum, 2);
                subMeth3();
            }
            System.out.println("esum : "+ esum);
        }
    }
    public void subMeth1(){
        for (int i=0;i<10;i++){
            double acc = 0;
            for (int j=0;j<101;j++){
                acc = acc + x1[j] * wh[i][j];
            }
            x2[i] = 1/(1+Math.exp(-acc));
        }


    }
    public void subMeth2(){
        x3 = 0;
        double acc1 = 0;
        for (int i1=0;i1<10;i1++){
            acc1 = acc1 + x2[i1]*wo[i1];

        }
        x3 = 1/(1+Math.exp(-acc1));
        double correct = 0;
        elet = correct - x3;
        if(correct == 1){
            elet = elet - elet*5;
        }
    }
    public void subMeth3(){
        double slopeo = 0;
        double slopeh = 0;
        double dx3dw = 0;
        for (int i=0;i<10;i++){
            for(int j =0;j<101;j++){
                slopeo = x3 *(1-x3);
                slopeh = x2[i] * (1-x2[i]);
                dx3dw = x1[j]*slopeh*wo[i]*slopeo;
                wh[i][j] = wh[i][j] + dx3dw*elet*MU;

            }
        }
        for(int i=0;i<10;i++){
            slopeo = x3*(1-x3);
            dx3dw = x2[i]*slopeo;
            wo[i] = wo[i] + dx3dw * elet * MU;

        }
    }

}
