package com.houduan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.houduan.service.IQuestionscommentService;
import com.houduan.entity.Questionscomment;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author online_system
 * @since 2022-08-12
 */
@RestController
@RequestMapping("/questionscomment")
    public class QuestionscommentController {

@Resource
private IQuestionscommentService questionscommentService;

@PostMapping
public Boolean save(@RequestBody Questionscomment questionscomment) {
        return questionscommentService.saveOrUpdate(questionscomment);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return questionscommentService.removeById(id);
        }

@GetMapping
public List<Questionscomment> findAll() {
        return questionscommentService.list();
        }

@GetMapping("/{id}")
public Questionscomment findOne(@PathVariable Integer id) {
        return questionscommentService.getById(id);
        }

@GetMapping("/page")
public Page<Questionscomment> findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize) {
        return questionscommentService.page(new Page<>(pageNum, pageSize));
        }

        }

