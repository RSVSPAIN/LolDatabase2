package com.example.dam2a.loldatabase2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    DatabaseReference ref;

    Map<String, String> imageUrls = new HashMap<>();
    Map<String, String> champKeys = new HashMap<>();  // aatrox => -7Gheiuiewgew
    List<Champ> champs = new ArrayList<>(500);
    List<Bans> bans = new ArrayList<>(500);
    List<Build> build = new ArrayList<>(500);
    List<Video> video = new ArrayList<>(500);

    boolean champsLoaded = false;
    boolean imagesLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.sign_in2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });

        ref = FirebaseDatabase.getInstance().getReference();

        queryImages();
        queryChamps();
//        try {
//            uploadImages();
//        }catch (InterruptedException e) {
//           e.printStackTrace();
//        }
//        try {
//            uploadVideos();
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        uploadVideoActivity();
//        uploadChamps();
//        uploadBansGeneral();
//        uploadBansBronce();
//        uploadBansPlata();
//        uploadBansOro();
//        uploadBansPlatino();
//        uploadBansDiamante();

    }

    void finishedLoading(){
        if(imagesLoaded && champsLoaded){
            uploadBuilds();
        }
    }

    void uploadChamps(){
        champs.clear();
        champs.add(new Champ(0, getString(R.string.title_top), "ic_aatrox", getString(R.string.title_aatrox)));
        champs.add(new Champ(1, getString(R.string.title_mid),"ic_ahri",getString(R.string.title_ahri)));
        champs.add(new Champ(2, getString(R.string.title_mid),"ic_akali",getString(R.string.title_akali)));
        champs.add(new Champ(3, getString(R.string.title_support),"ic_alistar",getString(R.string.title_alistar)));
        champs.add(new Champ(4, getString(R.string.title_jungla),"ic_amumu",getString(R.string.title_amumu)));
        champs.add(new Champ(5, getString(R.string.title_mid),"ic_anivia",getString(R.string.title_anivia)));
        champs.add(new Champ(6, getString(R.string.title_mid),"ic_annie",getString(R.string.title_annie)));
        champs.add(new Champ(7, getString(R.string.title_adc),"ic_ashe",getString(R.string.title_ashe)));
        champs.add(new Champ(8, getString(R.string.title_mid),"ic_aurelion_sol",getString(R.string.title_aurelion_sol)));
        champs.add(new Champ(9, getString(R.string.title_mid),"ic_azir",getString(R.string.title_azir)));
        champs.add(new Champ(10, getString(R.string.title_support),"ic_bardo",getString(R.string.title_bardo)));
        champs.add(new Champ(11, getString(R.string.title_support),"ic_blitzcrank",getString(R.string.title_blitzcrank)));
        champs.add(new Champ(12, getString(R.string.title_mid),"ic_brand",getString(R.string.title_brand)));
        champs.add(new Champ(13, getString(R.string.title_support),"ic_braum",getString(R.string.title_braum)));
        champs.add(new Champ(14, getString(R.string.title_adc),"ic_caitlyn",getString(R.string.title_caitlyn)));
        champs.add(new Champ(15, getString(R.string.title_top),"ic_camille",getString(R.string.title_camille)));
        champs.add(new Champ(16, getString(R.string.title_mid),"ic_cassiopeia",getString(R.string.title_cassiopeia)));
        champs.add(new Champ(17, getString(R.string.title_top),"ic_chogath",getString(R.string.title_cho_gath)));
        champs.add(new Champ(18, getString(R.string.title_mid),"ic_corki",getString(R.string.title_corki)));
        champs.add(new Champ(19, getString(R.string.title_top),"ic_darius",getString(R.string.title_darius)));
        champs.add(new Champ(20, getString(R.string.title_mid),"ic_diana",getString(R.string.title_diana)));
        champs.add(new Champ(21, getString(R.string.title_top),"ic_dr_mundo",getString(R.string.title_dr_mundo)));
        champs.add(new Champ(22, getString(R.string.title_adc),"ic_draven",getString(R.string.title_draven)));
        champs.add(new Champ(23, getString(R.string.title_mid),"ic_ekko",getString(R.string.title_ekko)));
        champs.add(new Champ(24, getString(R.string.title_jungla),"ic_elise",getString(R.string.title_elise)));
        champs.add(new Champ(25, getString(R.string.title_jungla),"ic_evelynn",getString(R.string.title_evelynn)));
        champs.add(new Champ(26, getString(R.string.title_adc),"ic_ezreal",getString(R.string.title_ezreal)));
        champs.add(new Champ(27, getString(R.string.title_jungla),"ic_fiddlesticks",getString(R.string.title_fiddlesticks)));
        champs.add(new Champ(28, getString(R.string.title_top),"ic_fiora",getString(R.string.title_fiora)));
        champs.add(new Champ(29, getString(R.string.title_mid),"ic_fizz",getString(R.string.title_fizz)));
        champs.add(new Champ(30, getString(R.string.title_top),"ic_galio",getString(R.string.title_galio)));
        champs.add(new Champ(31, getString(R.string.title_top),"ic_gangplank",getString(R.string.title_gangplank)));
        champs.add(new Champ(32, getString(R.string.title_top),"ic_garen",getString(R.string.title_garen)));
        champs.add(new Champ(33, getString(R.string.title_top),"ic_gnar",getString(R.string.title_gnar)));
        champs.add(new Champ(34, getString(R.string.title_jungla),"ic_gragas",getString(R.string.title_gragas)));
        champs.add(new Champ(35, getString(R.string.title_jungla),"ic_graves",getString(R.string.title_graves)));
        champs.add(new Champ(36, getString(R.string.title_jungla),"ic_hecarim",getString(R.string.title_hecarim)));
        champs.add(new Champ(37, getString(R.string.title_mid),"ic_heimerdinger",getString(R.string.title_heimerdinger)));
        champs.add(new Champ(38, getString(R.string.title_top),"ic_illaoi",getString(R.string.title_illaoi)));
        champs.add(new Champ(39, getString(R.string.title_top),"ic_irelia",getString(R.string.title_irelia)));

        for (final Champ champ: champs) {
            writeNewChamp(champ);
        }
    }

    void uploadBansGeneral() {
        bans.clear();
        bans.add(new Bans(0, "ic_aatrox","Aatrox","50,28%","0,17%","1,71%"));
        bans.add(new Bans(1, "ic_ahri","Ahri","52,29%","0,21%","5,80%"));
        bans.add(new Bans(2, "ic_akali","Akali","49,94%","0,50%","4,32%"));
        bans.add(new Bans(3, "ic_alistar","Alistar","51,16%","1,25%","7,03%"));
        bans.add(new Bans(4, "ic_amumu","Amumu","51,62%","0,04%","2,01%"));
        bans.add(new Bans(5, "ic_anivia","Anivia","54,48%","0,14%","2,81%"));
        bans.add(new Bans(6, "ic_annie","Annie","48,33%","0,12%","2,59%"));
        bans.add(new Bans(7, "ic_ashe","Ashe","49,59%","0,04%","5,04%"));
        bans.add(new Bans(8, "ic_aurelion_sol","Aurelion Sol","52,06%","0,07%","1,09%"));
        bans.add(new Bans(9, "ic_azir","Azir","47,50%","0,77%","6,11%"));
        bans.add(new Bans(10, "ic_bardo","Bardo","51,34%","0,08%","4,78%"));
        bans.add(new Bans(11, "ic_blitzcrank","Blitzcrank","51,80%","2,52%","8,02%"));
        bans.add(new Bans(12, "ic_brand","Brand","53,12%","0,56%","7,07%"));
        bans.add(new Bans(13, "ic_braum","Braum","51,52%","0,40%","4,74%"));
        bans.add(new Bans(14, "ic_caitlyn","Caitlyn","46,97%","0,32%","7,17%"));
        bans.add(new Bans(15, "ic_camille","Camille","49,63%","0,71%","10,3%"));
        bans.add(new Bans(16, "ic_cassiopeia","Cassiopeia","47,71%","0,09%","2,67%"));
        bans.add(new Bans(17, "ic_chogath","Cho'Gath","48,54%","0,50%","2,75%"));
        bans.add(new Bans(18, "ic_corki","Corki","50,26%","0,04%","2,60%"));
        bans.add(new Bans(19, "ic_darius","Darius","50,51%","2,00%","5,61%"));
        bans.add(new Bans(20, "ic_diana","Diana","51,68%","0,24%","3,34%"));
        bans.add(new Bans(21, "ic_dr_mundo","Dr. Mundo","49,01%","0,05%","2,01%"));
        bans.add(new Bans(22, "ic_draven","Draven","49,36%","1,40%","8,51%"));
        bans.add(new Bans(23, "ic_ekko","Ekko","50,35%","0,15%","5,52%"));
        bans.add(new Bans(24, "ic_elise","Elise","50,45%","0,20%","3,46%"));
        bans.add(new Bans(25, "ic_evelynn","Evelynn","52,72%","3,45%","7,18%"));
        bans.add(new Bans(26, "ic_ezreal","Ezreal","51,41%","2,95%","17,7%"));
        bans.add(new Bans(27, "ic_fiddlesticks","Fiddlesticks","49,94%","0,06%","2,32%"));
        bans.add(new Bans(28, "ic_fiora","Fiora","49,82%","1,38%","6,16%"));
        bans.add(new Bans(29, "ic_fizz","Fizz","52,36%","1,54%","5,18%"));
        bans.add(new Bans(30, "ic_galio","Galio","47,89%","0,09%","1,81%"));
        bans.add(new Bans(31, "ic_gangplank","Gangplank","51,86%","0,95%","10,9%"));
        bans.add(new Bans(32, "ic_garen","Garen","50,81%","0,24%","3,74%"));
        bans.add(new Bans(33, "ic_gnar","Gnar","48,11%","0,28%","5,12%"));
        bans.add(new Bans(34, "ic_gragas","Gragas","47,52%","0,03%","2,76%"));
        bans.add(new Bans(35, "ic_graves","Graves","48,41%","0,10%","5,71%"));
        bans.add(new Bans(36, "ic_hecarim","Hecarim","48,75%","0,13%","2,67%"));
        bans.add(new Bans(37, "ic_heimerdinger","Heimerdinger","52,18%","0,09%","1,88%"));
        bans.add(new Bans(38, "ic_illaoi","Illaoi","51,84%","1,79%","6,48%"));
        bans.add(new Bans(39, "ic_irelia","Irelia","50,25%","0,23%","3,99%"));

        for (final Bans ban: bans) {
            writeNewBan(ban, "all-champs");
        }

    }

    void uploadBansBronce() {
        bans.clear();
        bans.add(new Bans(12, "ic_brand","Brand","55,34%","12,52%","11,82%"));
        bans.add(new Bans(11, "ic_blitzcrank","Blitzcrank","53,36%","32,60%","10,54%"));
        bans.add(new Bans(38, "ic_illaoi","Illaoi","54,47%","25,90%","08,79%"));
        bans.add(new Bans(3, "ic_alistar","Alistar","52,49%","08,57%","11,66%"));

        for (final Bans ban: bans) {
            writeNewBan(ban, "bronce");
        }

    }

    void uploadBansPlata () {
        bans.clear();
        bans.add(new Bans(12, "ic_brand","Brand","55,34%","12,52%","11,82%"));
        bans.add(new Bans(11, "ic_blitzcrank","Blitzcrank","53,36%","32,60%","10,54%"));
        bans.add(new Bans(38, "ic_illaoi","Illaoi","54,47%","25,90%","08,79%"));
        bans.add(new Bans(3, "ic_alistar","Alistar","52,49%","08,57%","11,66%"));

        for (final Bans ban: bans) {
            writeNewBan(ban, "plata");
        }
    }

    void uploadBansOro () {
        bans.clear();
        bans.add(new Bans(26, "ic_ezreal","Ezreal","52,81%","25,25%","23,35%"));
        bans.add(new Bans(12, "ic_brand","Brand","54,95%","12,52%","11,82%"));
        bans.add(new Bans(11, "ic_blitzcrank","Blitzcrank","50,78%","32,60%","10,54%"));
        bans.add(new Bans(3, "ic_alistar","Alistar","53,89%","08,57%","11,66%"));

        for (final Bans ban: bans) {
            writeNewBan(ban, "oro");
        }
    }

    void uploadBansPlatino () {
        bans.clear();
        bans.add(new Bans(26, "ic_ezreal","Ezreal","52,85%","25,25%","23,35%"));
        bans.add(new Bans(25, "ic_evelynn","Evelynn","54,31%","36,30%","08,16%"));
        bans.add(new Bans(3, "ic_gangplank","Gangplank","53,97%","05,76%","10,49%"));
        bans.add(new Bans(3, "ic_alistar","Alistar","52,71%","08,57%","11,66%"));

        for (final Bans ban: bans) {
            writeNewBan(ban, "platino");
        }
    }

    void uploadBansDiamante() {
        bans.clear();
        bans.add(new Bans(26, "ic_ezreal","Ezreal","58,76%","25,25%","23,35%"));
        bans.add(new Bans(3, "ic_gangplank","Gangplank","65,92%","05,76%","10,49%"));
        bans.add(new Bans(12, "ic_brand","Brand","57,96%","12,52%","11,82%"));
        bans.add(new Bans(29, "ic_evelynn","Fizz","61,69%","12,59%","07,80%"));

        for (final Bans ban: bans) {
            writeNewBan(ban, "diamante");
        }
    }

    void uploadBuilds() {
        build.clear();
        build.add(new Build("Aatrox", "50,28%","Top","2013","ic_aatrox", "ic_tabi_ninja", "ic_trinidad", "ic_sterak", "ic_hidra_titanica", "ic_rostro_espiritual", "ic_rey_arruinado", "ic_flash", "ic_teleport", "","", "ic_espada_de_doran", "ic_pocion","ic_aatrox_lvl", "ic_precision_round", "ic_domination", "ic_brujeria", "ic_valor", "ic_inspiracion", "ic_domination", "ic_brujeria", "ic_valor_round", "ic_inspiracion", "ic_garras_del_inmortal_round", "ic_inquebrantable", "ic_piel_de_hierro_round", "ic_sobrecrecimiento_round", "ic_reverberacion", "ic_demoler", "ic_concha_espejo", "ic_revitalizar", "ic_protector", "ic_fuente_de_vida_round", "ic_condicionamiento", "ic_fuentes_renovadas", "ic_super_curacion", "ic_leyenda_presteza_round", "ic_golpe_de_gracia", "ic_triumfo", "ic_leyenda_tenacidad", "ic_derribado", "ic_claridad_mental", "ic_leyenda_linaje", "ic_ultimo_esfuerzo_round"));
        build.add(new Build("Ahri", "50,28%","Top","2013","ic_ahri", "ic_tabi_ninja", "ic_trinidad", "ic_sterak", "ic_hidra_titanica", "ic_rostro_espiritual", "ic_rey_arruinado", "ic_flash", "ic_teleport", "","", "ic_espada_de_doran", "ic_pocion","ic_aatrox_lvl", "ic_precision_round", "ic_domination", "ic_brujeria", "ic_valor", "ic_inspiracion", "ic_domination", "ic_brujeria", "ic_valor_round", "ic_inspiracion", "ic_garras_del_inmortal_round", "ic_inquebrantable", "ic_piel_de_hierro_round", "ic_sobrecrecimiento_round", "ic_reverberacion", "ic_demoler", "ic_concha_espejo", "ic_revitalizar", "ic_protector", "ic_fuente_de_vida_round", "ic_condicionamiento", "ic_fuentes_renovadas", "ic_super_curacion", "ic_leyenda_presteza_round", "ic_golpe_de_gracia", "ic_triumfo", "ic_leyenda_tenacidad", "ic_derribado", "ic_claridad_mental", "ic_leyenda_linaje", "ic_ultimo_esfuerzo_round"));


        for (final Build build : build){
            writeNewBuild(build);
        }
    }

    void uploadVideoActivity() {
        video.clear();
        video.add(new Video(0,"Zed vs Zed (Faker)","fakerzed"));
        video.add(new Video(1,"Backdoor (Xpeke)","backdoor"));

        for (final Video video: video){
            writeNewVideo(video);
        }
    }

    void uploadImages() throws InterruptedException {
        try {
            for(final String imageFileName: getAssets().list("img")) {
                StorageReference champsRef = FirebaseStorage.getInstance().getReference().child(UUID.randomUUID().toString());
                UploadTask uploadTask = champsRef.putStream(getAssets().open("img/" + imageFileName));
                Thread.sleep(300);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        System.out.println(downloadUrl.toString());
                        String imageStrip = imageFileName.replace(".png","");
                        System.out.println("IMAGESTRIP [" + imageStrip + "]");
                        imageUrls.put(imageStrip, downloadUrl.toString());
                        ref.child("images").child(imageStrip).setValue(downloadUrl.toString());
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void queryImages(){
        ref.child("images").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String imageUrl = dataSnapshot1.getValue(String.class);
                    String key = dataSnapshot1.getKey();
                    imageUrls.put(key, imageUrl);
                }

                imagesLoaded = true;
                finishedLoading();
           }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void uploadVideos() throws InterruptedException {
        try {
            for(final String videoFileName: getAssets().list("videos")) {
                StorageReference champsRef = FirebaseStorage.getInstance().getReference().child(UUID.randomUUID().toString());
                UploadTask uploadTask = champsRef.putStream(getAssets().open("videos/" + videoFileName));
                Thread.sleep(300);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        System.out.println(downloadUrl.toString());
                        String videoStrip = videoFileName.replace(".mp4","");
                        System.out.println("VIDEOSTRIP [" + videoStrip + "]");
                        imageUrls.put(videoStrip, downloadUrl.toString());
                        ref.child("videos").child(videoStrip).setValue(downloadUrl.toString());
                    }
                });

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void queryChamps(){
        ref.child("champs/all-champs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Champ champ = dataSnapshot1.getValue(Champ.class);
                    String key = dataSnapshot1.getKey();
                    champKeys.put(champ.name, key);
                }

                champsLoaded = true;
                finishedLoading();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void writeNewChamp(final Champ champ){

        Champ champFB = new Champ(champ.id, champ.posicion, imageUrls.get(champ.imageName), champ.name);
        String champKey = ref.child("champs").push().getKey();
        champKeys.put(champ.name,champKey);

        ref.child("champs").child("all-champs").child(champKey).setValue(champFB);

        if ("Top".equals(champ.getPosicion())) {
            ref.child("champs/top-champs").child(champKey).setValue(champ.getName().toLowerCase());
        }
        if ("Jungla".equals(champ.getPosicion())) {
            ref.child("champs/jungla-champs").child(champKey).setValue(champ.getName().toLowerCase());
        }
        if ("Mid".equals(champ.getPosicion())) {
            ref.child("champs/mid-champs").child(champKey).setValue(champ.getName().toLowerCase());
        }
        if ("Adc".equals(champ.getPosicion())) {
            ref.child("champs/adc-champs").child(champKey).setValue(champ.getName().toLowerCase());
        }
        if ("Support".equals(champ.getPosicion())) {
            ref.child("champs/support-champs").child(champKey).setValue(champ.getName().toLowerCase());
        }
    }

    void writeNewBan(final Bans ban, final String refbans){
        ref.child("images").child(ban.imageName).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String imageUrl = dataSnapshot.getValue(String.class);
                Bans BansFB = new Bans(ban.id, imageUrl, ban.name, ban.victorias, ban.banrate, ban.pickrate);
                String champKey = ref.child("bans").push().getKey();
                ref.child("bans").child(refbans).child(champKey).setValue(BansFB);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    void writeNewBuild(Build build){

        Map<String, String> UrlImagenes = new HashMap<>();

        for (String key: build.imagenes.keySet()){
            System.out.println("IMAGEURL " + key +  " ==>> " + build.imagenes.get(key));

            UrlImagenes.put(key, imageUrls.get(build.imagenes.get(key)));
        }

        build.imagenes = UrlImagenes;

        ref.child("build").child(champKeys.get(build.name)).setValue(build);

    }

    void writeNewVideo(final Video video){
        ref.child("videos").child(video.videoName).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String videoUrl = dataSnapshot.getValue(String.class);
                Video videoFB = new Video(video.id,video.titulo,videoUrl);
                String champKey = ref.child("videos").push().getKey();
                ref.child("videos").child(champKey).setValue(videoFB);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
