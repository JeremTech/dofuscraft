package fr.dofuscraft.dofuscraftcore.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ClassInfoParser {private static JsonParser jsonParser = new JsonParser();

    private static String file;
    private static String content = "";

    public ClassInfoParser(String fileNameToParse)
    {
        this.file = fileNameToParse;
        this.readFile();
    }

    public ArrayList<String> getResourceInfo(int id)
    {
        // Creation de la liste
        ArrayList<String> resource_infos = new ArrayList<>(3);

        // On parse le contenu du fichier si le contenu n'est pas vide
        if(!content.isEmpty())
        {
            JsonElement jsonTree = jsonParser.parse(content);

            // On vérifie qu'en sortie du parseur on a bien du json
            if(jsonTree.isJsonObject())
            {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                JsonElement jsonElement = jsonObject.get("" + id);

                // On parse le sous groupe de nom "id"
                if(jsonElement.isJsonObject())
                {
                    JsonObject jsonObject1 = jsonElement.getAsJsonObject();

                    // On ajoute à la liste les informations du sous groupe
                    resource_infos.add(0, jsonObject1.get("icon").getAsString());
                    resource_infos.add(1, jsonObject1.get("name").getAsString());
                    resource_infos.add(2, jsonObject1.get("level").getAsString());
                    resource_infos.add(3, jsonObject1.get("damage").getAsString());
                    resource_infos.add(4, jsonObject1.get("info").getAsString());

                    return resource_infos;
                }
            }
        }

        return null;
    }

    public int getNumberOfResources()
    {
        // On parse le contenu du fichier si le contenu n'est pas vide
        if(!content.isEmpty())
        {
            JsonElement jsonTree = jsonParser.parse(content);

            // On vérifie qu'en sortie du parseur on a bien du json
            if(jsonTree.isJsonObject())
            {
                JsonObject jsonObject = jsonTree.getAsJsonObject();
                return jsonObject.size();
            }
        }

        return 0;
    }

    private void readFile()
    {
        String line = null;

        try
        {
            // On lit le fichier
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/dofuscraftcore/classes/" + file), "UTF-8"));

            // On remet à zéro la vraible "content"
            content = "";

            // On ajoute les lignes à la suite de "content" tant qu'il y en a
            while((line = bufferedReader.readLine()) != null)
            {
                content += line;
            }

            // On ferme le fichier
            bufferedReader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Unable to open file 'assets/dofuscraftcore/classes/" + file + "'");
        }
        catch (IOException e)
        {
            System.out.println("Error reading file 'assets/dofuscraftcore/classes/" + file + "'");
        }
    }
}