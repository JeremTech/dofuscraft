package fr.dofuscraft.dofuscraftcore.utils.parsers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class JobInfoJsonParser extends DCJsonParser
{
    public JobInfoJsonParser(String fileToParse)
    {
        super(fileToParse);

        try
        {
            this.fileToRead = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/dofuscraftcore/jobs/" + this.file), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        this.readFile();
        this.countJsonElements();

        System.out.println("Content : " + this.content);
    }

    @Override
    public ArrayList<String> getInformations(int id)
    {
        // Creation de la liste
        ArrayList<String> resource_infos = new ArrayList<>(4);

        // On parse le contenu du fichier si le contenu n'est pas vide
        if(!this.content.isEmpty())
        {
            JsonElement jsonTree = jsonParser.parse(this.content);

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
                    resource_infos.add(0, jsonObject1.get("resource").getAsString());
                    resource_infos.add(1, jsonObject1.get("level").getAsString());
                    resource_infos.add(2, jsonObject1.get("reward").getAsString());
                    resource_infos.add(3, jsonObject1.get("location").getAsString());

                    return resource_infos;
                }
            }
        }

        return null;
    }
}
