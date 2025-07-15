<template>
  <div class="hs_indx_title_main_wrapper">
    <div class="hs_title_img_overlay" />
    <div class="container">
      <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 full_width">
          <div class="hs_indx_title_left_wrapper">
            <h2>Reflective Question</h2>
          </div>
        </div>
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12  full_width">
          <div class="hs_indx_title_right_wrapper">
            <ul>
              <li><a href="#">Birth Chart</a> &nbsp;&nbsp;&nbsp;> </li>
              <li>Reflective Question</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="hs_contact_indx_form_main_wrapper">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
          <div class="hs_about_heading_main_wrapper">
            <div class="hs_about_heading_wrapper">
              <h2>Question To Reflect Your Life </h2>
              <h4><span>&nbsp;</span></h4>
              <p>Proin gravida nibh vel velit auctor aliquet. Aenean sollicitudin, lorem quis bibendum<br> auctor, nisi elit consequat hello Aenean world.</p>
            </div>
          </div>
        </div>
        <form>
          <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="hs_vs_first_sec_wrapper">
              <h2>Family & Roots</h2>
              <p>Do you feel safe and emotionally supported when you think about your family or childhood home? Why or why not?</p>
              <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="hs_kd_six_sec_input_wrapper i-message">
                  <textarea
                    rows="6"
                    name="message"
                    class="require"
                    placeholder="Answer Here"
                  />
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="hs_vs_first_sec_wrapper">
              <h2>Emotional Depth</h2>
              <p>How comfortable are you with feeling and expressing intense emotions like grief, anger, or desire?</p>
              <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="hs_kd_six_sec_input_wrapper i-message">
                  <textarea
                    rows="6"
                    name="message"
                    class="require"
                    placeholder="Answer Here"
                  />
                </div>
              </div>
            </div>
          </div>  
          <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="hs_vs_first_sec_wrapper">
              <h2>Control & Trust</h2>
              <p>Do you often feel the need to control situations or people to feel safe? How easy is it for you to trust others?</p>
              <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="hs_kd_six_sec_input_wrapper i-message">
                  <textarea
                    rows="6"
                    name="message"
                    class="require"
                    placeholder="Answer Here"
                  />
                </div>
              </div>
            </div>
          </div>      
          <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="hs_vs_first_sec_wrapper">
              <h2>Loss & Transformation</h2>
              <p>Have you gone through any emotionally intense experiences (loss, betrayal, crisis) that fundamentally changed you?</p>
              <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="hs_kd_six_sec_input_wrapper i-message">
                  <textarea
                    rows="6"
                    name="message"
                    class="require"
                    placeholder="Answer Here"
                  />
                </div>
              </div>
            </div>
          </div>          
          <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="response" />
            <div class="hs_kd_six_sec_btn">
              <ul>
                <li>
                  <input
                    type="hidden"
                    name="form_type"
                    value="contact"
                  >
                  <router-link
                    class="hs_btn_hover"
                    to="/JourneyDetail"
                  >Refecletive Question</router-link>
                </li>
              </ul>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>


</template>
