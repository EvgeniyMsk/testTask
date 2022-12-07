package com.axiomatika.test.controllers;

import com.axiomatika.test.dto.EquationDTO;
import com.axiomatika.test.exceptions.EquationException;
import com.axiomatika.test.responses.IncorrectEquationMessage;
import com.axiomatika.test.services.SolutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
@Slf4j
@Tag(name="Главный контроллер", description="Решение квадратного уравнения")
public class MainController {
    private final SolutionService service;
    @Value("${messages.incorrectEquation}")
    private String INCORRECT_EQUATION_MESSAGE;

    @Autowired
    public MainController(SolutionService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(
            summary = "runTask",
            description = "Пытаемся решить квадратное уравнение. На вход поступает " +
                    "JSON с полями a,b,c - коэффициенты квадратного уравнения. " +
                    "Программа сохраняет значения коэффициентов и рассчитанных корней уравнения в базу данных." +
                    "Пользователю возвращается JSON с решением уравнения. В случае, если найти корни уравнения нельзя, " +
                    "возвращается ошибка"
    )
    public ResponseEntity<Object> runTask(@RequestBody EquationDTO equationDTO) {
        try {
            return ResponseEntity.ok(service.solve(equationDTO));
        } catch (EquationException e) {
            log.info("MainController.runTask: " + INCORRECT_EQUATION_MESSAGE);
            return ResponseEntity.ok(new IncorrectEquationMessage(INCORRECT_EQUATION_MESSAGE));
        } catch (Exception e) {
            log.info("MainController.runTask: Bad request returned");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
