package tk.tagirkhamitov.chromelinksserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tk.tagirkhamitov.chromelinksserver.data.Record;
import tk.tagirkhamitov.chromelinksserver.service.RecordService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class MainController {
    private final RecordService service;
    public MainController(RecordService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String getAllRecords(Model model) {
        List<Record> records = service.getAllRecords();
        model.addAttribute("records", records);
        return "all_records";
    }

    @GetMapping("/add/{area}")
    public String addRecordWithArea(@PathVariable String area,
                                    @RequestParam(name = "t") String target,
                                    Model model) {
        return addRecordWithAreaAndName(area, "", target, model);
    }

    @GetMapping("/add/{area}/{name}")
    public String addRecordWithAreaAndName(@PathVariable String area,
                                           @PathVariable String name,
                                           @RequestParam(name = "t") String target,
                                           Model model) {
        model.addAttribute("area", area);
        model.addAttribute("name", name);
        model.addAttribute("target", target);
        service.addRecord(area, name, target);
        return "added";
    }

    @GetMapping("/del/{area}/{name}")
    public String deleteRecord(@PathVariable String area, @PathVariable String name, Model model) {
        model.addAttribute("area", area);
        model.addAttribute("name", name);
        service.deleteRecord(area, name);
        return "deleted";
    }

    @GetMapping("/del/{area}")
    public String deleteByArea(@PathVariable String area,
                               @RequestParam(name = "all", required = false, defaultValue = "false") String all,
                               Model model) {
        if (all.equals("true")) {
            model.addAttribute("area", area);
            service.deleteRecordsByArea(area);
            return "deleted_area";
        } else {
            return deleteRecord(area, "", model);
        }
    }

    @GetMapping("/**")
    public String pageNotFound() {
        return "404";
    }
}
