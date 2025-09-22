package com.thinkinnovative.library_management_system.service.serviceImpl;

import com.thinkinnovative.library_management_system.dto.WidgetDTO;
import com.thinkinnovative.library_management_system.dto.WidgetsListDTO;
import com.thinkinnovative.library_management_system.entity.Widget;
import com.thinkinnovative.library_management_system.repository.WidgetRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
@RequiredArgsConstructor
@Service
public class WidgetService {

   // @Autowired
    private final WidgetRepository widgetRepository;

    // Initialize the logger for this class
    private static final Logger logger = LoggerFactory.getLogger(WidgetService.class);
//private JdbcTemplate jdbcTemplate;
    private final JdbcTemplate jdbcTemplate;


    public String addWidgets(WidgetDTO widgetDTO){

    Widget widget = new Widget();
    widget.setWidget_name(widgetDTO.getWidget_name());
    widget.setDescription(widgetDTO.getDescription());
    widget.setCreated_at(LocalDateTime.now());
    widget.setIs_active(1);
    widget.setIcon(widgetDTO.getIcon());
    widget.setColor(widget.getColor());
    widget.setType(widgetDTO.getType());
    widget.setBackgroundColor(widgetDTO.getBackgroundColor());
    widgetRepository.save(widget);

    return "The widgets are added successfully";
}

    public List<Map<String, Object>> getWidget(String type) {
    List<Map<String, Object>> responseList = new ArrayList<>();
    String query = "";
    List<Object> params = new ArrayList<>();

    try
    {
        if (type != null && !type.isEmpty()) {
            List<String> typeList = Arrays.asList(type.split(","));
            String inClause = String.join(",", Collections.nCopies(typeList.size(), "?"));
            query = "SELECT w.widget_name AS widgetName, w.description, w.icon, w.onClickPath, w.isCommon, w.type, w.color, w.backgroundColor " +
                    "FROM project.widgets w " +
                    "WHERE w.type IN (" + inClause + ") AND w.is_active = 1 " +
                    "ORDER BY w.widget_name";
            params.addAll(typeList);
        }

        List<Map<String, Object>> widgets = jdbcTemplate.queryForList(query, params.toArray());


        if (widgets == null || widgets.isEmpty()) {
            return responseList;
        }

        for (Map<String, Object> widget : widgets) {
            Map<String, Object> widgetResponse = new HashMap<>();
            String widgetType = widget.get("widgetName").toString();
            widgetResponse.put("type", widgetType);

            try{
                switch (widget.get("widgetName").toString()) {
                    case "totalBooks":
                        Integer totalBooks = getBooks();
                        widgetResponse.put("value", totalBooks);

                }
            } catch (Exception e)
            {
                logger.error("Error processing widget: " + widget.get("widgetName"), e);
                widgetResponse.put("value", "-");
            }
            responseList.add(widgetResponse);
        }
    }
    catch (Exception e)
    {

        return responseList;
    }




    return responseList;
}

    private Integer getBooks() {
        String baseQuery = "SELECT COUNT(DISTINCT a.bookid) " +
                "FROM project.library_information a " ;

         List<Object> params = new ArrayList<>();


        return jdbcTemplate.queryForObject(baseQuery, Integer.class);
    }

    public List<WidgetsListDTO> getWidgets()
    {
        return widgetRepository.getWidgets();
    }

    public String updateWidgets(Integer widget_id, WidgetDTO widgetDTO) {
        Optional<Widget> widgetToUpdate = widgetRepository.findById(widget_id);
        Widget widget = widgetToUpdate.get();
        widget.setWidget_name(widgetDTO.getWidget_name());
        widget.setDescription(widgetDTO.getDescription());
        widget.setUpdated_at(LocalDateTime.now());
        widget.setIs_active(1);
        widget.setIcon(widgetDTO.getIcon());
        widget.setColor(widget.getColor());
        widget.setType(widgetDTO.getType());
        widget.setBackgroundColor(widgetDTO.getBackgroundColor());
        widgetRepository.save(widget);
       return "The widget updated successfully";
    }

    public String updateStatus(Integer widget_id, WidgetDTO widgetDTO){

        Optional<Widget> widgetToUpdate = widgetRepository.findById(widget_id);
        Widget widget = widgetToUpdate.get();
        Integer status = widgetDTO.getIs_active();
        widget.setIs_active(status);
        widgetRepository.save(widget);
        return (status==0)?"The widget is deactivated successfully":"The widget is activated successfully";
    }



}
