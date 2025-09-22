package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.dto.WidgetsListDTO;
import com.thinkinnovative.library_management_system.entity.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WidgetRepository extends JpaRepository<Widget, Integer> {

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.WidgetsListDTO(w.widget_name, w.description, w.created_at, w.updated_at, w.is_active)" +
            "FROM Widget w " +
            "WHERE w.is_active=1")
    List<WidgetsListDTO> getWidgets();

}
