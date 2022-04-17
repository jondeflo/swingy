package ru.school21.swingy.Controller;

import ru.school21.swingy.Model.*;
import ru.school21.swingy.View.GameView;

import java.io.*;
import java.util.ArrayList;

public class SaveHandler {

    public ArrayList<Hero> readSaves(ArrayList<Artifact> artList) throws Exception {

        ArrayList<Hero> list = new ArrayList<>();
        File file = new File("./data/swingy.sav");
        FileInputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);

        String line;

        while ((line = reader.readLine()) != null) {

            String[] arr = line.split(";");
            Hero hero = new Hero(arr[0], arr[1], // name, type
                    Integer.parseInt(arr[2]), // level
                    Integer.parseInt(arr[3]), // exp
                    Integer.parseInt(arr[4]), // attack
                    Integer.parseInt(arr[5]), // defence
                    Integer.parseInt(arr[6])); // hit points
            for (int i = 7; i < arr.length; i++) {
                String artName = arr[i];
                for (Artifact a : artList) {
                    if (artName.equals(a.getName())) {
                        hero.equipUnit(a);
                    }
                }
            }

            list.add(hero);
        }
        reader.close();
        isr.close();
        is.close();

        if (list.size() > 0)
            return list;
        else
            return null;
    }

    public Hero getHeroFromView(ArrayList<Hero> heroList, GameView view) {
        return view.loadHero(heroList);
    }


    public ArrayList<String> readEnemies() throws Exception {
        ArrayList<String> enemyList = new ArrayList<>();
        File file = new File("./data/villains.txt");
        FileInputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);

        String line;
        while ((line = reader.readLine()) != null && line.length() > 0)
            enemyList.add(line);

        return enemyList;
    }

    public ArrayList<Artifact> readArtifacts () throws Exception
    {
        ArrayList<Artifact> artList = new ArrayList<>();
        File file = new File("./data/weapon.txt");
        FileInputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);

        String line;

        while ((line = reader.readLine()) != null && line.length() > 0) {
            String[] tmp = line.split(";");
            double lvl = Integer.parseInt(tmp[0]) * 10.0;
            int random = (int) (lvl * 0.75 + (Math.random() * (lvl * 0.5)));
            artList.add(new Weapon(tmp[1], "Weapon",
                    Integer.parseInt(tmp[0]) - 1, random, 0,0));
        }

        file = new File("./data/armor.txt");
        is = new FileInputStream(file);
        isr = new InputStreamReader(is);
        reader = new BufferedReader(isr);
        while ((line = reader.readLine()) != null && line.length() > 0) {
            String[] tmp = line.split(";");
            double lvl = Integer.parseInt(tmp[0]) * 10.0;
            int random = (int) (lvl * 0.75 + (Math.random() * (lvl * 0.5)));
            artList.add(new Armor(tmp[1], "Armor",
                    Integer.parseInt(tmp[0]) - 1, 0, random, 0));
        }

        file = new File("./data/helm.txt");
        is = new FileInputStream(file);
        isr = new InputStreamReader(is);
        reader = new BufferedReader(isr);
        while ((line = reader.readLine()) != null && line.length() > 0) {
            String[] tmp = line.split(";");
            double lvl = Integer.parseInt(tmp[0]) * 10.0;
            int random = (int) (lvl * 0.75 + (Math.random() * (lvl * 0.5)));
            artList.add(new Helm(tmp[1], "Helm",
                    Integer.parseInt(tmp[0]) - 1, 0, 0, random));
        }

        reader.close();
        isr.close();
        is.close();
        return artList;
    }

    private String getArtifactString(Hero hero)
    {
        String artifacts = "";
        if (hero.artifactInfo().length() > 0) {
            if (hero.getWeapon() != null)
                artifacts += hero.getWeapon().getName() + ";";
            if (hero.getArmor() != null)
                artifacts +=  hero.getArmor().getName() + ";";
            if (hero.getHelm() != null)
                artifacts += hero.getHelm().getName() + ";";
        }

        return artifacts;
    }

    private void writeHero(PrintWriter writer, Hero hero)
    {
        String artInfo = getArtifactString(hero);
        hero.unequip();
        writer.print(hero.getName() + ";");
        writer.print(hero.getType() + ";");
        writer.print(hero.getLevel() + ";");
        writer.print(hero.getExp() + ";");
        writer.print(hero.getAttack() + ";");
        writer.print(hero.getDefence() + ";");
        writer.print(hero.getHit() + ";");

        if (artInfo.length() > 0)
            writer.print(artInfo);
        writer.println();
    }

    public void saveHero(Hero hero, ArrayList<Hero> heroList) {

        try (PrintWriter writer = new PrintWriter("./data/swingy.sav", "UTF-8")) {
           if (heroList != null) { // write all loaded Heroes to a new save
               for (Hero tmpHero : heroList) {
                   if (!tmpHero.getName().equals(hero.getName())) {
                        writeHero(writer, tmpHero);
                   }
               }
           }

            writeHero(writer, hero);

        } catch (IOException e) {
            System.out.println("Smth went wrong during save");
            e.printStackTrace();
            System.exit(1);
        }
    }
}




