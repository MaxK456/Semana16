
    package Archivos;
    import Modelo.Pago;
    import java.io.*;
    import java.util.LinkedList;
    import java.util.Queue;
    
    public class ArchivoPagos {
        //guarda la cola de pagos al archivo
        public static void guardar(Queue<Pago>pagos){
            try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("pagos.dat"))){
                oos.writeObject(pagos);
            }catch(Exception e){
                System.out.println("error al guardar pagos");
            }
            }
        //carga la cola de pagos
        public static Queue<Pago> cargar(){
            File arch=new File("pagos.dat");
            //verifica si el archivo existe
            if(!arch.exists()){
                return new LinkedList<>();
            }
            try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(arch))) {
                return (Queue<Pago>) ois.readObject();
            }catch(Exception e){
                return new LinkedList<>();

                    }
        }
        }
        