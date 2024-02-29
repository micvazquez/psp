import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        String texto = "En un lugar de la Mancha de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor. Una olla de algo más vaca que carnero, salpicón las más noches, duelos y quebrantos los sábados, lentejas los viernes, algún palomino de añadidura los domingos, consumían las tres partes de su hacienda. El resto della concluían sayo de vellarte, calzas de velludo para las fiestas con sus pantuflos de lo mismo, y los días de entresemana se honraba con su vellorí de lo más fino";

        try {
            //CALCULAREMOS EL RESUMEN HASH SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(texto.getBytes());

            // ALMACENAMOS EL HASH EN EL ARCHIVO
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("QUIJOTE_HASH.DAT"));
            outputStream.writeObject(hash);
            outputStream.close();

            System.out.println("Resumen SHA-256 del Quijote creado con éxito");
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }


        // VERIFICAMOS QUE PODEMOS LEER EL ARCHVIO QUIJOTE_HASH.DAT
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("QUIJOTE_HASH.DAT"));
            byte[] storedHash = (byte[]) inputStream.readObject();
            inputStream.close();

            // IMPRIMIR EL HASH DEL ARCHIVO
            System.out.println("Hash almacenado: " + bytesToHex(storedHash));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
// CONVERTIR ARRAY DE BYTES A UNA CADENA HEXADECIMAL


// EXPLIACIÓN DE COMO SUBIR UN REPOSITORIO A GITHUB DESDE LA MÁQUINA LOCAL
/* 1. Cremos el repositorio en Github y lo configuramos con nuestras preferencias
* 2. Con Git lo inicaizalzamos con, git init
* 3. Luego pondremos los siguiente comandos, git add. y git add ..
* 4. Luego confiramos los cambios con, git commit
* 5. Ponemos la URL de Github, git remote add origin https://github.com/micvazquez/psp.git
* 6. Subimos los cambios al repositorio, git branch -M main
* 7. Esto subirá tus cambios al branch "master" en el repositorio remoto llamado "origin", git push -u origin main
* */