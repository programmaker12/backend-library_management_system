package com.thinkinnovative.library_management_system.controller;

import com.thinkinnovative.library_management_system.dto.WidgetDTO;
import com.thinkinnovative.library_management_system.dto.WidgetsListDTO;
import com.thinkinnovative.library_management_system.service.serviceImpl.WidgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/widgets")
public class WidgetController {

    private final WidgetService widgetService;

    @Autowired
    public WidgetController(WidgetService widgetService) {
        this.widgetService = widgetService;
    }

    @GetMapping("/dashboard")
    public List<Map<String, Object>> getWidgets(@RequestParam(value = "type", required = false, defaultValue = "totalBooks") String type) {
        return widgetService.getWidget(type);
    }

    @PostMapping("/addWidgets")
    public String addwidgets(@RequestBody WidgetDTO widgetDTO) {
        widgetService.addWidgets(widgetDTO);
        return "The widgets are added successfully";
    }

    @GetMapping("/list")
    public List<WidgetsListDTO> getAllWidgets() {
            return widgetService.getWidgets();
        }

    @PutMapping("/update/{widget_id}")
    public String updateWidget(@PathVariable Integer widget_id, @RequestBody WidgetDTO widgetDTO) {
        return widgetService.updateWidgets(widget_id, widgetDTO);
    }

    @PutMapping("/status/{widget_id}")
    public String updateStatus(@PathVariable Integer widget_id, @RequestBody WidgetDTO widgetDTO)
    {

        return widgetService.updateStatus(widget_id,widgetDTO);

    }



}
