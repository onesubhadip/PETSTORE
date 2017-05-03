'use strict';

const gulp = require('gulp');
const concat = require('gulp-concat');
const del = require('del');
const runSequence = require('run-sequence');

//include scripts
const scripts = require('./config/scripts');
const styles = require('./config/styles');


var devMode = false;

gulp.task('clean', function () {
    return del([
        // delete everything inside the build folder
        './build/**/*'
    ]);
});

gulp.task('css', function(){
	return gulp.src(styles)
		.pipe(concat('main.css'))
		.pipe(gulp.dest('build/css'))
});


gulp.task('js', function(){
	return gulp.src(scripts)
		.pipe(concat('mainApp.js'))
		.pipe(gulp.dest('build/js'))
});

gulp.task('html', function(cb){
	gulp.src('./src/templates/**/*.html')
	.pipe(gulp.dest('build/templates'));
	return gulp.src('./src/*.html').pipe(gulp.dest('build'));
});

gulp.task('image', function(cb){
	return gulp.src('./src/image/**/*').pipe(gulp.dest('build/image'));
});

gulp.task('build', function(cb){
	runSequence( 
		'clean', ['css', 'js', 'html', 'image'], 
		cb);
});

gulp.task('serve', function(){
	devMode=true;
	gulp.start(['build']);
});