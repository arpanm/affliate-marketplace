import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, isNumber, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getVideoUsers } from 'app/entities/video-user/video-user.reducer';
import { getEntities as getVideoPosts } from 'app/entities/video-post/video-post.reducer';
import { createEntity, getEntity, reset, updateEntity } from './review.reducer';

export const ReviewUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const videoUsers = useAppSelector(state => state.videoUser.entities);
  const videoPosts = useAppSelector(state => state.videoPost.entities);
  const reviewEntity = useAppSelector(state => state.review.entity);
  const loading = useAppSelector(state => state.review.loading);
  const updating = useAppSelector(state => state.review.updating);
  const updateSuccess = useAppSelector(state => state.review.updateSuccess);

  const handleClose = () => {
    navigate('/review');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getVideoUsers({}));
    dispatch(getVideoPosts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.rating !== undefined && typeof values.rating !== 'number') {
      values.rating = Number(values.rating);
    }

    const entity = {
      ...reviewEntity,
      ...values,
      reviewer: videoUsers.find(it => it.id.toString() === values.reviewer?.toString()),
      post: videoPosts.find(it => it.id.toString() === values.post?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...reviewEntity,
          reviewer: reviewEntity?.reviewer?.id,
          post: reviewEntity?.post?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="affliateMarketplaceApp.review.home.createOrEditLabel" data-cy="ReviewCreateUpdateHeading">
            <Translate contentKey="affliateMarketplaceApp.review.home.createOrEditLabel">Create or edit a Review</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="review-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.isLiked')}
                id="review-isLiked"
                name="isLiked"
                data-cy="isLiked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.isSkipped')}
                id="review-isSkipped"
                name="isSkipped"
                data-cy="isSkipped"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.isDisliked')}
                id="review-isDisliked"
                name="isDisliked"
                data-cy="isDisliked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.isWatched')}
                id="review-isWatched"
                name="isWatched"
                data-cy="isWatched"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.isFullyWatched')}
                id="review-isFullyWatched"
                name="isFullyWatched"
                data-cy="isFullyWatched"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.isReported')}
                id="review-isReported"
                name="isReported"
                data-cy="isReported"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.rating')}
                id="review-rating"
                name="rating"
                data-cy="rating"
                type="text"
                validate={{
                  min: { value: 1, message: translate('entity.validation.min', { min: 1 }) },
                  max: { value: 5, message: translate('entity.validation.max', { max: 5 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.comment')}
                id="review-comment"
                name="comment"
                data-cy="comment"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.reportReason')}
                id="review-reportReason"
                name="reportReason"
                data-cy="reportReason"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.isBlocked')}
                id="review-isBlocked"
                name="isBlocked"
                data-cy="isBlocked"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.isModerated')}
                id="review-isModerated"
                name="isModerated"
                data-cy="isModerated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.isActive')}
                id="review-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.createdBy')}
                id="review-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.createdOn')}
                id="review-createdOn"
                name="createdOn"
                data-cy="createdOn"
                type="date"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.updatedBy')}
                id="review-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('affliateMarketplaceApp.review.updatedOn')}
                id="review-updatedOn"
                name="updatedOn"
                data-cy="updatedOn"
                type="date"
              />
              <ValidatedField
                id="review-reviewer"
                name="reviewer"
                data-cy="reviewer"
                label={translate('affliateMarketplaceApp.review.reviewer')}
                type="select"
              >
                <option value="" key="0" />
                {videoUsers
                  ? videoUsers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="review-post"
                name="post"
                data-cy="post"
                label={translate('affliateMarketplaceApp.review.post')}
                type="select"
              >
                <option value="" key="0" />
                {videoPosts
                  ? videoPosts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/review" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ReviewUpdate;
