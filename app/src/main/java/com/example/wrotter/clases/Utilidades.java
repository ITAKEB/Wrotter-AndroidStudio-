package com.example.wrotter.clases;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;

import com.example.wrotter.R;
import com.example.wrotter.clases.vo.AprendidasVo;
import com.example.wrotter.clases.vo.AvatarVo;
import com.example.wrotter.clases.vo.EjemploVo;
import com.example.wrotter.clases.vo.JugadorVo;
import com.example.wrotter.clases.vo.PalabrasVo;
import com.example.wrotter.clases.vo.PreguntaVo;

import java.util.ArrayList;
import java.util.Collections;

public class Utilidades {
    public static ArrayList<AvatarVo> listaAvatars= null;

    public static ArrayList<JugadorVo> listaJugadores= null;

    public static ArrayList<PreguntaVo> listaPreguntas=null;

    public static ArrayList<PalabrasVo> listaPalabras= null;

    public static ArrayList<EjemploVo> listaEjemplos=null;

    public static ArrayList<AprendidasVo> listaAprendidas=null;

    public static AvatarVo avatarSeleccion=null;

    public static int avatarIdSeleccion=1;

    public static final String NOMBRE_BD="wrotter_bd";

    public static final String TABLA_JUGADOR="jugador";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_CONTRASEÑA="contraseña";
    public static final String CAMPO_AVATAR="avatar";
    public static final String CAMPO_PUNTAJE="puntaje";
    public static final String CAMPO_ULTIMA_PALRABRA="ultima";
    public static final String CAMPO_TEMA="tema";
    public static final String CAMPO_PUNTACION_MAXIMA="maxima";

    public static final String TABLA_PALABRAS_APRENDIDAS="aprendidas";
    public static final String CAMPO_ID_PALABRA="palabras";
    public static final String CAMPO_ID_JUGADOR="jugador";

    public static final String CREAR_TABLA_JUGADOR="CREATE TABLE "+TABLA_JUGADOR+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_NOMBRE+" TEXT,"+CAMPO_CONTRASEÑA+" TEXT,"+CAMPO_AVATAR+" INTEGER,"+CAMPO_PUNTAJE+" INTEGER,"+CAMPO_ULTIMA_PALRABRA+" INTEGER,"+CAMPO_TEMA+" INTEGER,"+CAMPO_PUNTACION_MAXIMA+" INTEGER)";
    public static final String CREAR_TABLA_PALABRA_APRENDIDAS="CREATE TABLE "+TABLA_PALABRAS_APRENDIDAS+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAMPO_ID_PALABRA+ " INTEGER,"+CAMPO_ID_JUGADOR+" INTEGER)";

    public static final String textoEnNegrita = "Java_Prof_Level";

    public static void obtenerListaAvatars(){
        listaAvatars= new ArrayList<AvatarVo>();

        listaAvatars.add(new AvatarVo(1, R.drawable.ovatar1,"Avatar1"));
        listaAvatars.add(new AvatarVo(2, R.drawable.ovatar2,"Avatar2"));
        listaAvatars.add(new AvatarVo(3, R.drawable.ovatar3,"Avatar3"));
        listaAvatars.add(new AvatarVo(4, R.drawable.ovatar4,"Avatar4"));
        listaAvatars.add(new AvatarVo(5, R.drawable.ovatar5,"Avatar5"));
        listaAvatars.add(new AvatarVo(6, R.drawable.ovatar6,"Avatar6"));
        listaAvatars.add(new AvatarVo(7, R.drawable.ovatar7,"Avatar7"));
        listaAvatars.add(new AvatarVo(8, R.drawable.ovatar8,"Avatar8"));
        listaAvatars.add(new AvatarVo(9, R.drawable.ovatar9,"Avatar9"));
        listaAvatars.add(new AvatarVo(10, R.drawable.ovatar10,"Avatar10"));
        listaAvatars.add(new AvatarVo(11, R.drawable.ovatar11,"Avatar11"));
        listaAvatars.add(new AvatarVo(12, R.drawable.ovatar12,"Avatar12"));
        listaAvatars.add(new AvatarVo(13, R.drawable.ovatar13,"Avatar13"));
        listaAvatars.add(new AvatarVo(14, R.drawable.ovatar14,"Avatar14"));
        listaAvatars.add(new AvatarVo(15, R.drawable.ovatar15,"Avatar15"));

        avatarSeleccion=listaAvatars.get(0);
    }


    public static void obtenerListaPalabras(){
        listaPalabras = new ArrayList<PalabrasVo>();

        listaPalabras.add(new PalabrasVo(0,"ACMÈ","Momento en que algo está en su máximo esplendor. Con ese sentido figurado se usa en medicina, filosofía, ontología y estética."));
        listaPalabras.add(new PalabrasVo(1,"ABUHADO","Se utiliza para referirse a lo ‘Hinchado o abotagado’."));
        listaPalabras.add(new PalabrasVo(2,"AMOVER","Destituir, deponer a alguien de su empleo o destino."));
        listaPalabras.add(new PalabrasVo(3,"SUPERFLUO","Que no cumple ni desempeña una función.\n" + "Que no es innecesario o que está de más."));
        listaPalabras.add(new PalabrasVo(4,"INEFABLE","Hace referencia a algo que no puede ser dicho, explicado o descrito con palabras, generalmente por tener cualidades excelsas o por ser muy sutil o difuso."));
        listaPalabras.add(new PalabrasVo(5,"INCONMENSURABLE","Que es muy difícil o imposible de medir o valorar."));
        listaPalabras.add(new PalabrasVo(6,"ETÉREO","Que es intangible o poco definido y, a la vez, sutil o sublime."));
        listaPalabras.add(new PalabrasVo(7,"SUBLIME","Que es extraordinariamente bello y produce una gran emoción."));
        listaPalabras.add(new PalabrasVo(8,"SEMPITERNO","Que durará siempre, que no tendrá fin."));
        listaPalabras.add(new PalabrasVo(9,"MONDO","Limpio y libre de cosas añadidas o superfluas."));
        listaPalabras.add(new PalabrasVo(10,"RESILIENCIA","Capacidad que tiene una persona para superar circunstancias traumáticas"));
        listaPalabras.add(new PalabrasVo(11,"ACENDRADO","Puro, sin mancha ni deseño"));
        listaPalabras.add(new PalabrasVo(12,"PERENTORIO","Que es determinante, decisivo o definitivo, urgente."));
        listaPalabras.add(new PalabrasVo(13,"DENODADO","Que tiene o muestra valor, energía o decisión."));
        listaPalabras.add(new PalabrasVo(14,"BAHÚNO","Conjunto de gente soez, ordinario, vil, abyecto, despreciable y grosero."));
        listaPalabras.add(new PalabrasVo(15,"ADUCIR","Argumentos o pruebas para justificar algo."));
        listaPalabras.add(new PalabrasVo(16,"ENARDECER","Excitar o avivar algo, especialmente un sentimiento, el entusiasmo o el apetito sexual, en una persona."));
        listaPalabras.add(new PalabrasVo(17,"DIGNIFICAR","Dar respeto a algo."));
        listaPalabras.add(new PalabrasVo(18,"BAHAREQUE","Pared de palos."));
        listaPalabras.add(new PalabrasVo(19,"CABAL","Sinonimo o forma de decir \"Perfecto\" o \"Preciso\" "));
        listaPalabras.add(new PalabrasVo(20,"ABNEGACIÓN","Sacrificio que alguien hace de su voluntad"));
        listaPalabras.add(new PalabrasVo(21,"RICTUS","Mueca de la boca."));
        listaPalabras.add(new PalabrasVo(22,"ABIGEATO","Hurto de ganado o animales de crianza"));


    }

    public static void obtenerListaPreguntas(){
        listaPreguntas = new ArrayList<PreguntaVo>();



        listaPreguntas.add(new PreguntaVo(0,"¿Qué palabra define el máximo esplendor de algo?","Acmé","Mínimo","Sexual","Vivienda","Acmé"));
        listaPreguntas.add(new PreguntaVo(1,"¿De qué es sinonimo la palabra Abuhado?","Hinchado","Asado","Hematoma","Prisionero","Hinchado"));
        listaPreguntas.add(new PreguntaVo(2,"¿Qué significa ser amovido?","Ser Ascendido en un trabajo","Que lo despidan de un trabajo","Ser contratado en un trabajo","Moverse en línea recta","Que lo despidan de un trabajo"));
        listaPreguntas.add(new PreguntaVo(3,"¿A que palabra se la atribuye la definición de ser algo innecesario?","Peliagudo","Correspondencia","Reflujo","Superfluo","Superfluo"));
        listaPreguntas.add(new PreguntaVo(4,"Lo que sentí por esa mujer fue \"inefable\" ¿La palabra entre comillas corresponde a:","Algo que no puede ser dicho u expresado","Odio","Amor a primera vista","Hambre","Algo que no puede ser dicho u expresado"));
        listaPreguntas.add(new PreguntaVo(5,"¿Qué palabra es sinónimo de Inconmensurable?","Indescriptible","Dolor","Grande","Especialidad","Indescriptible"));
        listaPreguntas.add(new PreguntaVo(6,"Dios para mi es algo \"Etéreo\". La palabra entre comillas puede ser remplazada sin cambiar la intención de la oración por:","Divino","Intangible","Hermoso","Poco común","Intangible"));
        listaPreguntas.add(new PreguntaVo(7,"¿Qué palabra describe algo demasiado bello?","Correspondencia","Arte","Sublime","Orca","Sublime"));
        listaPreguntas.add(new PreguntaVo(8,"¿El hecho de que algo parezca no tener fin se le llama?","Pesca","Etéreo","Sempiterno","Superfluo","Sempiterno"));
        listaPreguntas.add(new PreguntaVo(9,"¿A qué se refiere la palabra mondo?","Palabra de barranquilla","Libre de superfluos","Mondongo (Comida)","Monto de cosas","Libre de superfluos"));
        listaPreguntas.add(new PreguntaVo(10,"¿Cómo se le llama a la capacidad de superar un trauma?","Superación","Resiliencia","Carácter","Basto","Resiliencia"));
        listaPreguntas.add(new PreguntaVo(11,"El valor de ese soldado es \"acendrado\". La palabra entre comillas puede ser sustituida por:","Increíble","Estúpido","Puro","De valorar","Puro"));
        listaPreguntas.add(new PreguntaVo(12,"¿Cuál es un sinónimo de perentorio?","Urgente","Difunto","Perfección","Alimento","Urgente"));
        listaPreguntas.add(new PreguntaVo(13,"¿Con cuál de la siguientes palabras se podría justificar algo?","Difunto","Perfección","Aducir","Resiliencia","Aducir"));
        listaPreguntas.add(new PreguntaVo(14,"La definición \"Excitar o avivar algo, especialmente un sentimiento, el entusiasmo o el apetito sexual, en una persona\". Corresponde a:","Aducir","Puro","Enardecer","Sublime","Enardecer"));
        listaPreguntas.add(new PreguntaVo(15,"Es una forma de dar respeto","Bahareque","Hematoma","Difunto","Dignificar","Dignificar"));
        listaPreguntas.add(new PreguntaVo(16,"¿Cuál de las siguientes opciones se refiere a una pared de palos?","Bahareque","Dignificar","Etéreo","Sempiterno","Bahareque"));
        listaPreguntas.add(new PreguntaVo(17,"¿Cuál de las siguientes es una forma de decir perfecto?","Resiliencia","Cabal","Difunto","Acmé","Cabal"));
        listaPreguntas.add(new PreguntaVo(18,"Es un sacrificio voluntario de una persona","Superfluo","Asado","Odio","Abnegación","Abnegación"));
        listaPreguntas.add(new PreguntaVo(19,"Sinóninomo de \"Mueca de la boca.\" ","Rictus","Aducir","Abnegación","Enardecer","Rictus"));
        listaPreguntas.add(new PreguntaVo(20,"Corresponde a un robo de vacas o animales de crianza","Enardecer","Abigeato","Rictus","Cabal","Abigeato"));
        listaPreguntas.add(new PreguntaVo(21,"Se nota que Eustaquio viene de una familia \"bahúna\". La palabra entre comillas puede ser sustituida por:","Etéreo","Ordinaria","Millonaria","Despreciable","Despreciable"));
        listaPreguntas.add(new PreguntaVo(22,"Conjunto de gente soez, ordinario, vil, abyecto, despreciable y grosero. ¿La anterior definición corresponde a?","Hematoma","Dolor","Bahúno","Cabal","Bahúmo"));
        listaPreguntas.add(new PreguntaVo(23,"Vamos a la reunión, hoy me siento \"Denodado\". La palabra entre comillas puede ser sustituida por:","Decidido","Feliz","Con suerte","Elocuente","Decidido"));
        listaPreguntas.add(new PreguntaVo(24,"Es determinante, urgente ,decisivo o definitivo, ¿Esta definición corresponde a? ","Epifanía","Perihelio","Perentorio","Concluir","Perentorio"));
        listaPreguntas.add(new PreguntaVo(25,"¿Qué palabra es sinónimo de puro?","Acendrado","Legendario","Elocuencia","Carambola","Acendrado"));
        listaPreguntas.add(new PreguntaVo(26,"¿Cuál es sinónimo de resiliencia?","Superación y capacidad","Ánimo y deseo","Carácter y empeño","Ayuda y lentes","Superación y capacidad"));
        listaPreguntas.add(new PreguntaVo(27,"Wikipedia parece \"sempiterna\". La palabra entre comillas puede ser sustituida por:","No tener fin","Aburrida","Finita","Sosa","No tener fin"));
        listaPreguntas.add(new PreguntaVo(28,"¿Qué significa que algo es intangible y a la vez sublime?","Etéreo","Efímero","Estéreo","Calamidad","Etéreo"));
        listaPreguntas.add(new PreguntaVo(29,"¿Qué palabra describe algo que es muy difícil de valorar?","Imposible","Inconmensurable","Catastrófico","Código","Inconmensurable"));

    }


    public static void consultarListaEjemplos(){

        listaEjemplos = new ArrayList<EjemploVo>();


        listaEjemplos.add(new EjemploVo(0,"Tras varios intentos fallidos de atrapar al correcaminos, el coyote se encuentra en el acmé de su depresión.","Estamos a punto del acmé de la película."));
        listaEjemplos.add(new EjemploVo(1,"¿No crees que Pinto está un poco más abuhado que la última vez?","El costal está un poco abuhado."));
        listaEjemplos.add(new EjemploVo(2,"Nadie puede amover a los políticos de hoy en día.","Amuevo a Simón del proyecto"));
        listaEjemplos.add(new EjemploVo(3,"La pregunta es superflua.","Que comentario tan superfluo."));
        listaEjemplos.add(new EjemploVo(4,"Fue totalmente inefable el momento que viví cuando vi a mi hijo nacer.","Es inefable el amor que siento por mi equipo de fútbol."));
        listaEjemplos.add(new EjemploVo(5,"Tenía joyas de un valor inconmensurable.","Cuando perdió a su esposa su soledad fue inconmensurable."));
        listaEjemplos.add(new EjemploVo(6,"Esa mujer es ciertamente etérea.","El amor de mi vida se fue hace 3 meses, quisiera sentir su mano, pero ahora no es más que algo etéreo."));
        listaEjemplos.add(new EjemploVo(7,"Solamente el arte puede hacer sentir lo sublime.","Las pecas de su cara hacían en conjunto un rostro verdaderamente sublime."));
        listaEjemplos.add(new EjemploVo(8,"El odio que siento por quien mato a mi padre es sempiterno.","Esta escalera parece sempiterna."));
        listaEjemplos.add(new EjemploVo(9,"No comió mas que una sopa monda.","Fue un discurso mondo, perfecto."));
        listaEjemplos.add(new EjemploVo(10,"La resiliencia trae la felicidad ","Ése chico tiene una gran resilencia"));
        listaEjemplos.add(new EjemploVo(11,"Defendía sus bienes con un acendrado instinto de propiedad.","Militar de acendrada carrera."));
        listaEjemplos.add(new EjemploVo(12,"Una resolución perentoria.","Necesito un médico es perentorio."));
        listaEjemplos.add(new EjemploVo(13,"Un joven con carácter denodado.","Vamos a la reunión, hoy me siento denodado."));
        listaEjemplos.add(new EjemploVo(14,"El conoció a una chica perfecta, lo único malo es que su familia era bahúna.","Pienso que ese grupo es bahúno."));
        listaEjemplos.add(new EjemploVo(15,"El aduce que deberiamos empezar a trabajar ya","Las encuestas aducirán nuestro proyecto"));
        listaEjemplos.add(new EjemploVo(16,"Deberias enardecer tu espiritu ","Mazo se enardece cuando ve unas piernas bonitas."));
        listaEjemplos.add(new EjemploVo(17,"Dignifico tus resultados en las pruebas","Debes dignificar a tus padres"));
        listaEjemplos.add(new EjemploVo(18,"Empieza a hacer el bahareque ","El bahareque se esta cayendo "));
        listaEjemplos.add(new EjemploVo(19,"Tus datos son cabales","Ësta carne está en su cabal"));
        listaEjemplos.add(new EjemploVo(20,"Haz una abnegacion por nosotros","Me gustaria que fueras más abnegado"));
        listaEjemplos.add(new EjemploVo(21,"Porque ese rictus? ","Quita ese rictus de tu cara"));
        listaEjemplos.add(new EjemploVo(22,"Voy a abigetear su propiedad ","Alguien abigeató mi rancho "));


    }


    public static void consultarListaJugadores(Activity actividad){

        ConexionSQliteHelper conn = new ConexionSQliteHelper(actividad,"bd_usuarios",null,1);
        SQLiteDatabase db= conn.getWritableDatabase();

        listaJugadores= new ArrayList<JugadorVo>();

            Cursor cursor= db.rawQuery("select * from "+TABLA_JUGADOR,null);
            //cursor.moveToFirst();
            while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String contraseña = cursor.getString(2);
            int avatar = cursor.getInt(3);
            int puntaje = cursor.getInt(4);
            listaJugadores.add(new JugadorVo(id,nombre,contraseña,avatar,puntaje));
            }
        conn.close();
    }

    public static void consultarListaPalabras(Activity actividad){

        ConexionSQliteHelper conn = new ConexionSQliteHelper(actividad,"bd_usuarios",null,1);
        SQLiteDatabase db= conn.getWritableDatabase();

        listaAprendidas= new ArrayList<AprendidasVo>();

        //listaAprendidas.add(new AprendidasVo(1,2,3));

        Cursor cursor= db.rawQuery("SELECT * FROM "+TABLA_PALABRAS_APRENDIDAS,null);
        System.out.println("Tabla");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int idPa = cursor.getInt(1);
            int idJu = cursor.getInt(2);
            listaAprendidas.add(new AprendidasVo(id,idPa,idJu));
        }
        System.out.println("Fin Tabla");
    }



    public static void ordenarListaJugadores(){
        Collections.sort(listaJugadores,Collections.<JugadorVo>reverseOrder());
    }





}
