package com.example.japan.model;

import java.util.ArrayList;

public class AudioStorage {
    private ArrayList<String> listAudio;

    public AudioStorage() {

    }
    public AudioStorage(ArrayList<String> listAudio) {
        this.listAudio = listAudio;
    }

    public ArrayList<String> getListAudioByIdLesson(int idLesson) {
        ArrayList<String> result = new ArrayList<>();
        if (idLesson == 1) {
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%201%2Finu.mp3?alt=media&token=e4b0869c-7835-4f0a-9c29-d3da34607474");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%201%2Fkumo.mp3?alt=media&token=b22b1d88-0f15-4096-b691-922c3c7076d9");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%201%2Fsaru.mp3?alt=media&token=7ac3f958-1a50-4d2a-88ac-c84eb805e1a7");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%201%2Fnezumi.mp3?alt=media&token=d47f110e-5893-417c-bcbf-87d4bac3625b");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%201%2Fkawaii.mp3?alt=media&token=9df843a8-6d61-445d-93e5-f815b9d21905");
        }
        if (idLesson == 2) {
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%202%2Fyasasii.mp3?alt=media&token=49aa91ed-4390-43ae-a631-6bd7f765fe0c");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%202%2Fageru.mp3?alt=media&token=b9089fc7-b119-43d4-930e-d44e6ffe3b27");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%202%2Fsageru.mp3?alt=media&token=f6f7a441-5cd1-4c58-bcff-61736017b94c");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%202%2Fmazeru.mp3?alt=media&token=b183e000-7804-44b9-ae59-72330f3ea216");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%202%2Ftobu.mp3?alt=media&token=947f8f4a-013b-4578-9c32-004f9968bee5");
        }
        if (idLesson == 3) {
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%203%2Fsensou.mp3?alt=media&token=87a2cf51-dd4f-4080-b6ca-357b975caeea");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%203%2Fsizen.mp3?alt=media&token=24152e33-9c06-4da1-90a5-214f1e08b7a7");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%203%2Fbunka.mp3?alt=media&token=0638c28a-1122-4e01-b37e-ee924fe36b41");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%203%2Fzairyou.mp3?alt=media&token=fc5b982d-7be5-4c8e-b8fa-3f104af1f788");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%203%2Fkawaku.mp3?alt=media&token=c3181683-29b6-486f-b163-254c263d0f08");
        }
        if(idLesson == 4) {
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fnureru.mp3?alt=media&token=6e1f4ede-ab1a-4ddf-adc5-b0e18919b64c");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fanzen.mp3?alt=media&token=7cb87b07-5f1d-47c6-b313-6a12f962100c");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkiken.mp3?alt=media&token=4c156d28-6fe4-4272-9d74-1e4b6516d8b0");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fnakanaka.mp3?alt=media&token=c867d3f6-eac0-46ef-8ff2-2575db0e327c");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fokazu.mp3?alt=media&token=3c480cf4-305c-4bee-964d-90ac7b8a0a0d");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Ftatoeba.mp3?alt=media&token=618915ec-d8ae-4cf3-bf3a-c090dcabd32d");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fbenri.mp3?alt=media&token=23d3c3c6-72f3-4414-aae4-9c1b96e1e746");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkuuki.mp3?alt=media&token=ecf42a67-5826-4711-9294-380f95b8a683");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fnamida.mp3?alt=media&token=66aed99c-8e57-4ead-9f06-5a035790752d");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Ftsunami.mp3?alt=media&token=7795f555-f53f-418d-874c-d161bb0c5105");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fzisin.mp3?alt=media&token=5aac2787-14a9-422a-9c37-49d8b33eae6f");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkaminari.mp3?alt=media&token=7a71e993-7bfc-4a02-93c8-0e8291857131");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fsiraseru.mp3?alt=media&token=5dd3ccc8-7bc9-45d8-a296-579d5078a8ed");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fnyuugakusuru.mp3?alt=media&token=a164d307-2cd9-45d8-9bc8-6429113fb775");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fsotsugyousuru.mp3?alt=media&token=283b882e-4487-4bb0-a744-7b452a72fc0c");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Frusu.mp3?alt=media&token=267f27ec-f23a-49a6-80a7-cac210be4adb");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fgenin.mp3?alt=media&token=30e4ed3f-48b5-442f-b5ae-81c4e5011896");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fberu.mp3?alt=media&token=7028e626-3eff-4f54-86b9-d3651e87a80a");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fnaru.mp3?alt=media&token=5e8f1a5f-4883-475f-9a6b-b8d057e21afb");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fguai.mp3?alt=media&token=85959167-838e-4f95-819d-648a612bd8e5");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkorobu.mp3?alt=media&token=82c740b6-2741-45ec-8a99-241c1f508d76");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkanozyo.mp3?alt=media&token=afcb2b2e-360a-48ab-b30b-0f5489aa796e");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkoibito.mp3?alt=media&token=a1044462-efcd-4e53-9615-b0bb75325d64");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fwakareru.mp3?alt=media&token=341a469c-7047-4ac0-8692-2bf75c4d8e8f");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkodomo.mp3?alt=media&token=68b18f8e-5177-499e-8722-a01fbe1d42c9");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fotona.mp3?alt=media&token=191ecae6-23ef-4ecf-b462-97521385ae58");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fakachan.mp3?alt=media&token=f4bd370b-9b30-429d-b89a-51e93f37ff8f");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fozyou.mp3?alt=media&token=095e214d-360c-40c6-807d-31ae63fb938e");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fdansei.mp3?alt=media&token=7875780e-2e7e-4cd2-9950-11e7caa7bdc8");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fzyosei.mp3?alt=media&token=bcef813f-4549-40e3-9bc7-6cdd8545d743");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkonogoro.mp3?alt=media&token=3bcba476-72b7-49e7-9bf3-ab073fdbf732");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkyoku.mp3?alt=media&token=b93421c0-e22e-4211-bf21-bef7b2c44332");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fkasyu.mp3?alt=media&token=668b84eb-31ab-4586-99be-731fe82bb7e6");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Futau.mp3?alt=media&token=c6962a99-6128-44de-9f5e-778876d96be5");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fsugiru.mp3?alt=media&token=6d8667f0-d8f6-4342-ab29-323d5362889f");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Frensyuusuru.mp3?alt=media&token=e3cb2916-b204-407b-9d49-4003c6ea4d38");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Ffutasyuusuru.mp3?alt=media&token=c58fe0d6-26d2-4c26-bff2-f5caf75fa6f2");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fotosu.mp3?alt=media&token=65fae59c-ff7f-446a-a52d-0c5d352a30bc");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fhirou.mp3?alt=media&token=e0d28909-f5fa-48cc-9f1a-326d9c5819f0");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fziyuu.mp3?alt=media&token=4687214f-0af8-4b36-9677-b3289cbb6b7b");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Friyuu.mp3?alt=media&token=75ec9ddf-d376-4398-83c7-da5b41457c9d");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fomote.mp3?alt=media&token=1c3cb206-6ea6-4d3f-84dd-387246c67b81");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fura.mp3?alt=media&token=7d1a3a99-5991-454d-8f15-21663c0f5929");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Fsiawase.mp3?alt=media&token=209d9a13-2c7c-4b62-b293-50144790b445");
            result.add("https://firebasestorage.googleapis.com/v0/b/testaudio-b6a62.appspot.com/o/B%C3%A0i%204%2Ftatoeba.mp3?alt=media&token=618915ec-d8ae-4cf3-bf3a-c090dcabd32d");
        }


        return result;


    }

    public ArrayList<String> addNewURLLink(String url) {
        ArrayList<String> listData = new ArrayList<>();
        listData.add(url);
        return listData;
    }


}
