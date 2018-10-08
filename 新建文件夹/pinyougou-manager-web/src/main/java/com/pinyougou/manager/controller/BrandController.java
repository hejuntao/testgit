package com.pinyougou.manager.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

import entity.BrandNameRepeatOrFirstCharNotFromatExection;
import entity.PageResult;
import entity.Result;



@RestController //组合注解
@RequestMapping("/brand") //地址映射
public class BrandController {

	@Reference
	private BrandService brandService;

	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		return brandService.findAll();
	}

	@RequestMapping("/findPage")
	public PageResult findPage(int page, int size){
		return brandService.findPage(page, size);
	}

	@RequestMapping("/add")
	public Result add(@RequestBody TbBrand brand){
		try {
			brandService.add(brand);
			return new Result(true, "加载成功");
		} catch (BrandNameRepeatOrFirstCharNotFromatExection e) {
			System.out.println(e.getMessage());
			return new Result(false, e.getMessage());
		}catch (Exception e) {
			return new Result(false, "加载失败");
		}
	}
	
	@RequestMapping("/update")
	public Result updateBrand(@RequestBody TbBrand brand){
		try {
			brandService.updateBrand(brand);
			return new Result(true, "更改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "更改失败");
		}
	}

	@RequestMapping("/findOne")
	public TbBrand findOne(long id){
		return brandService.findOne(id);
	}

	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			brandService.delete(ids);
			return new Result(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbBrand brand, int page, int size){
		return brandService.findPage(brand, page, size);

	}
	
	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList(){
		return brandService.selectOptionList();
	}
	
}
