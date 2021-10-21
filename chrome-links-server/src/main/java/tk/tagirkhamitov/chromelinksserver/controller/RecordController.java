package tk.tagirkhamitov.chromelinksserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.tagirkhamitov.chromelinksserver.data.Record;
import tk.tagirkhamitov.chromelinksserver.exception.RecordNotFoundException;
import tk.tagirkhamitov.chromelinksserver.service.RecordService;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/short")
public class RecordController {
    private static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @GetMapping("/wtf/{queryStr}")
    public String searchWikipedia(@PathVariable String queryStr) {
        queryStr = queryStr.toLowerCase();
        String prefix = "en";
        for (int i = 0; i < queryStr.length(); i++) {
            if (RUSSIAN_ALPHABET.indexOf(queryStr.charAt(i)) != -1) {
                prefix = "ru";
                break;
            }
        }
        String target = "https://" + prefix + ".wikipedia.org/wiki/" + queryStr;

        try {
            target = encodeUnicode(target);
        } catch (URISyntaxException e) {
            return defaultMapping();
        }

        return redirect(target);
    }

    @GetMapping("/{area}")
    public String areaOnly(@PathVariable String area) {
        return areaAndName(area, "");
    }

    @GetMapping("/{area}/{name}")
    public String areaAndName(@PathVariable String area, @PathVariable String name) {
        Record record = null;

        try {
            record = service.getRecord(area, name);
        } catch (RecordNotFoundException e) {
            if (area.equals("g")) {
                return redirect("https://github.com/" + name);
            }
            return defaultMapping();
        }

        String target = null;
        try {
            target = encodeUnicode(record.getTarget());
        } catch (URISyntaxException e) {
            return defaultMapping();
        }

        return redirect(target);
    }

    @GetMapping("/**")
    public String defaultMapping() {
        return "404";
    }

    private String encodeUnicode(String target) throws URISyntaxException {
        if (!target.startsWith("http://") && !target.startsWith("https://")) {
            target = "https://" + target;
        }
        URI uri = new URI(target);
        return uri.toASCIIString();
    }

    private String redirect(String url) {
        return "redirect:" + url;
    }
}
