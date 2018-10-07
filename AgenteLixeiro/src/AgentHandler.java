public class AgentHandler extends Thread {
    private Ambiente[][] ambiente;
    private int linhaAgente, colunaAgente;

    public AgentHandler(Ambiente[][] ambient){
        this.ambiente = ambient;
        encontraAgenteNoAmbiente();
    }

    @Override
    public void run(){
        try{
            while (true){
                Thread.sleep(2000);
                observer();
                imprimeAmbiente();
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
            }
        }catch (InterruptedException e){

        }
    }

    public void observer(){
        //Observa o ambiente
        try{
            if(ambiente[linhaAgente][colunaAgente+1].getClass().getName().equals("Lixo")){
                ambiente[linhaAgente][colunaAgente].setSeco(ambiente[linhaAgente][colunaAgente+1]);
                System.out.println("ENcontrei algo!!!");
            }

        }catch (NullPointerException e ){

        }catch (ArrayIndexOutOfBoundsException e){}
        moveRigth();
    }

    public void moveTop(){
        if(canIWalk(linhaAgente-1, colunaAgente)){
            ambiente[linhaAgente-1][colunaAgente] = ambiente[linhaAgente][colunaAgente];
            ambiente[linhaAgente][colunaAgente] = null;
            this.linhaAgente--;
        }
    }

    public void moveRigth(){
        if(canIWalk(linhaAgente, colunaAgente+1)){
            ambiente[linhaAgente][colunaAgente+1] = ambiente[linhaAgente][colunaAgente];
            ambiente[linhaAgente][colunaAgente] = null;
            this.colunaAgente++;
        }
    }

    public void moveBottom(){
        if(canIWalk(linhaAgente+1, colunaAgente)){
            ambiente[linhaAgente+1][colunaAgente] = ambiente[linhaAgente][colunaAgente];
            ambiente[linhaAgente][colunaAgente] = null;
            this.linhaAgente++;
        }
    }

    public void moveLeft(){
        if(canIWalk(linhaAgente, colunaAgente-1)){
            ambiente[linhaAgente][colunaAgente-1] = ambiente[linhaAgente][colunaAgente];
            ambiente[linhaAgente][colunaAgente] = null;
            this.colunaAgente--;
        }
    }

    public boolean canIWalk(int x, int y){
        try{
            if(ambiente[x][y] != null)
                return false;
        }catch (NullPointerException e){
            return false;
        }catch (ArrayIndexOutOfBoundsException ee){
            return false;
        }
        return true;
    }




















    public void encontraAgenteNoAmbiente(){
        for(int i = 0; i < ambiente.length; i++){
            for(int j = 0; j < ambiente[i].length; j++){
                if(ambiente[i][j] != null && ambiente[i][j].getObjectType().equals(" A ")){
                    this.linhaAgente = i;
                    this.colunaAgente = j;
                }
            }
        }
    }

    public void imprimeAmbiente(){
        for (int i = 0; i < ambiente.length; i++) {
            for (int j = 0; j < ambiente[i].length; j++) {
                if(ambiente[i][j] == null)
                    System.out.print(" - ");
                else
                    System.out.print(ambiente[i][j]);

                System.out.print(" | ");
            }
            System.out.println();
        }
    }



}
