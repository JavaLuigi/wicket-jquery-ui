/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.wicket.jquery.ui.calendar;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * Event listener shared by the {@link Calendar} widget and the {@link CalendarBehavior}
 *
 * @author Sebastien Briquet - sebfz1
 *
 */
interface ICalendarListener
{
	/**
	 * Indicates whether the event can be edited (ie, clicked).<br/>
	 * IIF true, an event can override this global setting to false by using CalendarEvent#setEditable(boolean);<br/>
	 * If true, the {@link #onEventClick(AjaxRequestTarget, CalendarView, int)} event and {@link #onDayClick(AjaxRequestTarget, CalendarView, Date, boolean)} event will be triggered<br/>
	 *
	 * @return false by default
	 * @deprecated will be removed in next version. Use {@link #isDayClickEnabled()} and/or {@link #isEventClickEnabled()} instead
	 */
	@Deprecated
	boolean isEditable();

	/**
	 * Indicates whether a cell can be selected.<br/>
	 * If true, the {@link #onSelect(AjaxRequestTarget, CalendarView, Date, Date, boolean)} event will be triggered
	 *
	 * @return false by default
	 */
	boolean isSelectable();

	/**
	 * Indicates whether a day can be clicked.<br/>
	 * If true, the {@link #onDayClick(AjaxRequestTarget, CalendarView, Date, boolean)} event will be triggered<br/>
	 * <b>Note:</b> <tt>true</tt> will enable the global 'editable' option to <tt>true</tt>.
	 *
	 * @return false by default
	 * @see CalendarEvent#setEditable(Boolean)
	 */
	boolean isDayClickEnabled();

	/**
	 * Indicates whether an event can be clicked.<br/>
	 * If true, the {@link #onEventClick(AjaxRequestTarget, CalendarView, int)} event will be triggered<br/>
	 * <b>Note:</b> <tt>true</tt> will enable the global 'editable' option to <tt>true</tt>.
	 *
	 * @return false by default
	 * @see CalendarEvent#setEditable(Boolean)
	 */
	boolean isEventClickEnabled();

	/**
	 * Indicates whether the {@link #onObjectDrop(AjaxRequestTarget, String, Date, boolean)} event will be triggered
	 *
	 * @return false by default
	 */
	boolean isObjectDropEnabled();

	/**
	 * Indicates whether the event can be dragged &#38; dropped. If true, the {@link #onEventDrop(AjaxRequestTarget, int, long, boolean)} event will be triggered
	 *
	 * @return false by default
	 */
	boolean isEventDropEnabled();

	/**
	 * Indicates whether the event can be resized. If true, the {@link #onEventResize(AjaxRequestTarget, int, long)} event will be triggered
	 *
	 * @return false by default
	 */
	boolean isEventResizeEnabled();

	/**
	 * Indicates whether the {@link #onViewRender(AjaxRequestTarget, CalendarView)} event will be triggered
	 *
	 * @return false by default
	 */
	boolean isViewRenderEnabled();

	/**
	 * Triggered when an cell is selected.<br/>
	 * {@link #isSelectable()} should return true for this event to be triggered.
	 *
	 * @param target the {@link AjaxRequestTarget}
	 * @param view the current calendar view
	 * @param start the event start {@link Date}
	 * @param end the event end {@link Date}
	 * @param allDay the event all-day property
	 */
	void onSelect(AjaxRequestTarget target, CalendarView view, Date start, Date end, boolean allDay);

	/**
	 * Triggered when a calendar day is clicked
	 *
	 * @param target the {@link AjaxRequestTarget}
	 * @param view the current calendar view
	 * @param date the day
	 * @param allDay the event all-day property
	 */
	void onDayClick(AjaxRequestTarget target, CalendarView view, Date date, boolean allDay);

	/**
	 * Triggered when an event is clicked.<br/>
	 * {@link #isEditable()} should return true for this event to be triggered.
	 *
	 * @param target the {@link AjaxRequestTarget}
	 * @param view the current calendar view
	 * @param eventId the {@link CalendarEvent} id
	 */
	void onEventClick(AjaxRequestTarget target, CalendarView view, int eventId);

	/**
	 * Triggered when an event is dropped (after being dragged).<br/>
	 * {@link #isEventDropEnabled()} should return true for this event to be triggered.
	 *
	 * @param target the {@link AjaxRequestTarget}
	 * @param eventId the {@link CalendarEvent} id
	 * @param delta the delta (time) with the original event date
	 * @param allDay the event all-day property
	 */
	void onEventDrop(AjaxRequestTarget target, int eventId, long delta, boolean allDay);

	/**
	 * Triggered when an event is dropped (after being dragged).<br/>
	 * {@link #isEventResizeEnabled()} should return true for this event to be triggered.
	 *
	 * @param target the {@link AjaxRequestTarget}
	 * @param eventId the {@link CalendarEvent} id
	 * @param delta the delta (time) with the original event date
	 */
	void onEventResize(AjaxRequestTarget target, int eventId, long delta);

	/**
	 * Triggered when an event-object is dropped
	 *
	 * @param target the {@link AjaxRequestTarget}
	 * @param title the title
	 * @param date the day
	 * @param allDay the event all-day property
	 */
	void onObjectDrop(AjaxRequestTarget target, String title, Date date, boolean allDay);

	/**
	 * Triggered when the calendar loads and every time a different date-range is displayed. {@link #isViewRenderEnabled()} should return true for this event to be triggered.
	 *
	 * @param target the {@link AjaxRequestTarget}
	 * @param view the current calendar view
	 */
	void onViewRender(AjaxRequestTarget target, CalendarView view);
}
