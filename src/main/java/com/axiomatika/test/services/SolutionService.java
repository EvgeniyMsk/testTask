package com.axiomatika.test.services;

import com.axiomatika.test.dto.EquationDTO;
import com.axiomatika.test.entities.Equation;
import com.axiomatika.test.entities.Solution;
import com.axiomatika.test.exceptions.EquationException;
import com.axiomatika.test.repository.SolutionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
@Slf4j
public class SolutionService {
    private final SolutionRepository solutionRepository;

    @Autowired
    public SolutionService(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Cacheable(value = "solution",key = "{ #equationDTO.a, #equationDTO.b, #equationDTO.c}")
    public Solution solve(EquationDTO equationDTO) throws InterruptedException {
        double a = equationDTO.getA();
        double b = equationDTO.getB();
        double c = equationDTO.getC();
        if (a == 0 & b == 0 & c == 0)
            throw new EquationException();
        double d = (Math.pow(b, 2) - (4 * a * c));
        if (d < 0)
        {
            throw new EquationException();
        }
        Equation equation = new Equation();
        equation.setA(a);
        equation.setB(b);
        equation.setC(c);
        Solution solution = new Solution();
        if (d == 0)
        {
            solution.setX1((-b) / (2 * a));
            solution.setX2(null);
            log.info("SolutionService.solve(): Successfully solved equation with 1 root");
        } else if (d > 0) {
            solution.setX1((-b - Math.sqrt(d)) / (2 * a));
            solution.setX2((-b + Math.sqrt(d)) / (2 * a));
            log.info("SolutionService.solve(): Successfully solved equation with 2 roots");
        }
        solution.setEquation(equation);
        Thread.sleep(4000L);
        simulateSlowService();
        return solutionRepository.save(solution);
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
