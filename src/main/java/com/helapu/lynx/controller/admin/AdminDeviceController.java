package com.helapu.lynx.controller.admin;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.helapu.lynx.entity.Device;
import com.helapu.lynx.service.IDeviceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.helapu.lynx.controller.admin.BaseWebController;
@Controller
@RequestMapping("/admin/devices")
public class AdminDeviceController extends BaseWebController {

	@Autowired
	private IDeviceService deviceService;
	
	// 设备列表
    @GetMapping("")
    public String list(ModelMap model) {
    	List<Device> deviceList = deviceService.list(null);
    	model.addAttribute("list", deviceList);
        return "admin/devices/list";
    }

    // 查看一个具体的设备
	@GetMapping("/{id}")
	public String detail(@PathVariable("id") Long deviceId, ModelMap model) {
		Device device = deviceService.getById(deviceId);
		model.addAttribute("device", device);
		return "admin/devices/show";
	}
	
    // 新增设备
	@GetMapping(params = "form")
	public String createForm(@ModelAttribute Device device) {
		return "admin/devices/form";
	}
	
    // 处理新增设备提交
	@PostMapping("")
	public ModelAndView create(@Valid Device device, BindingResult result,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("admin/devices/form", "formErrors", result.getAllErrors());
		}
		
		Boolean saveOk = deviceService.save(device);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
		return new ModelAndView("redirect:/{message.id}", "message.id", device.getId());
	}

	// 修改设备
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") Long deviceId, ModelMap model) {
		Device device = deviceService.getById(deviceId);
		model.addAttribute("device", device);
		return "admin/devices/edit";
	}
	
	// 删除设备
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		boolean deleteOk = deviceService.removeById(id);

		List<Device> devices = deviceService.list(null);
    	return new ModelAndView("admin/devices/index", "devices", devices);	}
	
    // 自定义提交
	@GetMapping(value = "modify/{id}")
	public ModelAndView modifyForm(@PathVariable("id") Device device) {
		return new ModelAndView("admin/devices/form", "device", device);
	}
	
	//
	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}
	
}

